/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.citer.domain;

import static de.weltraumschaf.citer.domain.RelTypes.A_CITE;
import static de.weltraumschaf.citer.domain.RelTypes.CREATED_BY;
import static de.weltraumschaf.citer.domain.RelTypes.REF_CITES;
import java.util.Map;
import java.util.UUID;
import org.joda.time.DateTime;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.index.Index;
import org.neo4j.helpers.collection.IterableWrapper;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CiteRepository implements Repository<Cite> {

    private final GraphDatabaseService graphDb;
    private final Index<Node> index;
    private final Node referenceNode;

    public CiteRepository(GraphDatabaseService graphDb, Index<Node> index) {
        this.graphDb = graphDb;
        this.index   = index;
        referenceNode  = getRootNode(graphDb);
    }

    private Node getRootNode(GraphDatabaseService graphDb) {
        Relationship rel = graphDb.getReferenceNode()
                                  .getSingleRelationship(REF_CITES, Direction.OUTGOING);

        if (null != rel) {
            return rel.getEndNode();
        } else {
            Transaction tx = graphDb.beginTx();

            try {
                Node refNode = graphDb.createNode();
                graphDb.getReferenceNode().createRelationshipTo(refNode, REF_CITES);
                tx.success();
                return refNode;
            } finally {
                tx.finish();
            }
        }
    }

    @Override
    public Cite create(Map<String, Object> params) {
        Transaction tx = graphDb.beginTx();

        try {
            Node newCiteNode = graphDb.createNode();
            referenceNode.createRelationshipTo(newCiteNode, A_CITE);

            for (String paramName : params.keySet()) {
                newCiteNode.setProperty(paramName, params.get(paramName));
            }

            String id = UUID.randomUUID().toString();
            DateTime now = new DateTime();
            newCiteNode.setProperty(Cite.ID, id);
            newCiteNode.setProperty(Cite.DATE_CREATED, now.toString());
            newCiteNode.setProperty(Cite.DATE_UPDATED, now.toString());
            index.add(newCiteNode, Cite.ID, id);
            tx.success();
            return new Cite(newCiteNode);
        } finally {
            tx.finish();
        }
    }

    @Override
    public Cite findById(String id) {
        Node citeNode = index.get(Cite.ID, id).getSingle();

        if (null == citeNode) {
            return null;
        }

        return new Cite(citeNode);
    }

    @Override
    public void delete(Cite cite) {
        Transaction tx = graphDb.beginTx();

        try {
            Node citeNode = cite.getUnderlyingNode();
            index.remove(citeNode, Cite.ID, cite.getId());
            citeNode.getSingleRelationship(CREATED_BY, Direction.OUTGOING).delete();
            citeNode.getSingleRelationship(A_CITE, Direction.INCOMING).delete();
            // @todo remove originator, if last cite of her.
            citeNode.delete();
            tx.success();
        } finally {
            tx.finish();
        }
    }

    @Override
    public Iterable<Cite> getAll() {
        return new IterableWrapper<Cite, Relationship>(referenceNode.getRelationships(A_CITE)) {
            @Override
            protected Cite underlyingObjectToObject(Relationship citeRel) {
                return new Cite(citeRel.getEndNode());
            }
        };
    }
}
