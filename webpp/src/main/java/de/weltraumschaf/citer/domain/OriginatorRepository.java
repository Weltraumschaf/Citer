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

import static de.weltraumschaf.citer.domain.RelTypes.A_ORIGINATOR;
import static de.weltraumschaf.citer.domain.RelTypes.REF_ORIGINATORS;
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
public class OriginatorRepository implements Repository<Originator> {

    private final GraphDatabaseService graphDb;
    private final Index<Node> indexById;
    private final Index<Node> indexByName;
    private final Node referenceNode;

    public OriginatorRepository(GraphDatabaseService graphDb, Index<Node> index, Index<Node> indexByName) {
        this.graphDb     = graphDb;
        this.indexById   = index;
        this.indexByName = indexByName;
        referenceNode    = getRootNode(graphDb);
    }

    private Node getRootNode(GraphDatabaseService graphDb) {
        Relationship rel = graphDb.getReferenceNode()
                                  .getSingleRelationship(REF_ORIGINATORS, Direction.OUTGOING);

        if (null != rel) {
            return rel.getEndNode();
        } else {
            Transaction tx = graphDb.beginTx();

            try {
                Node refNode = graphDb.createNode();
                graphDb.getReferenceNode().createRelationshipTo(refNode, REF_ORIGINATORS);
                tx.success();
                return refNode;
            } finally {
                tx.finish();
            }
        }
    }

    @Override
    public Originator create(Map<String, Object> params) throws Exception {
        Transaction tx = graphDb.beginTx();
        String name = (String)params.get(Originator.NAME);

        try {
            if (findByName(name) != null) {
                tx.failure();
                throw new Exception(String.format("Originator with name '%s' already exists! %s", name, findByName(name).toString()));
            }

            Node newOriginatorNode = graphDb.createNode();
            referenceNode.createRelationshipTo(newOriginatorNode, A_ORIGINATOR);

            for (String paramName : params.keySet()) {
                newOriginatorNode.setProperty(paramName, params.get(paramName));
            }

            String id = UUID.randomUUID().toString();
            DateTime now = new DateTime();
            newOriginatorNode.setProperty(Originator.ID, id);
            newOriginatorNode.setProperty(Originator.DATE_CREATED, now.toString());
            newOriginatorNode.setProperty(Originator.DATE_UPDATED, now.toString());
            indexById.add(newOriginatorNode, Originator.ID, id);
            indexByName.add(newOriginatorNode, Originator.NAME, name);
            tx.success();
            return new Originator(newOriginatorNode);
        } finally {
            tx.finish();
        }
    }

    public Originator findByName(String name) {
        Node originatorNode = indexByName.get(Originator.NAME, name).getSingle();

        if (null == originatorNode) {
            return null;
        }

        return new Originator(originatorNode);
    }

    @Override
    public Originator findById(String id) {
        Node originatorNode = indexById.get(Originator.ID, id).getSingle();

        if (null == originatorNode) {
            return null;
        }

        return new Originator(originatorNode);
    }

    @Override
    public void delete(Originator originator) {
        Transaction tx = graphDb.beginTx();

        try {
            Node originatorNode = originator.getUnderlyingNode();
            indexById.remove(originatorNode, Originator.ID, originator.getId());
            originatorNode.getSingleRelationship(A_ORIGINATOR, Direction.INCOMING).delete();
            // @todo remove originator, if last cite of her.
            originatorNode.delete();
            tx.success();
        } finally {
            tx.finish();
        }
    }

    @Override
    public Iterable<Originator> getAll() {
        return new IterableWrapper<Originator, Relationship>(referenceNode.getRelationships(A_ORIGINATOR)) {
            @Override
            protected Originator underlyingObjectToObject(Relationship originatorRel) {
                return new Originator(originatorRel.getEndNode());
            }
        };
    }
}
