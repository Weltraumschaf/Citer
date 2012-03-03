package de.weltraumschaf.citer.domain;

import static de.weltraumschaf.citer.domain.RelTypes.A_CITE;
import static de.weltraumschaf.citer.domain.RelTypes.REF_CITES;
import java.util.UUID;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.index.Index;
import org.neo4j.helpers.collection.IterableWrapper;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class CiteRepository {
    private final GraphDatabaseService graphDb;
    private final Index<Node> index;
    private final Node citeRefNode;

    public CiteRepository(GraphDatabaseService graphDb, Index<Node> index) {
        this.graphDb = graphDb;
        this.index   = index;
        citeRefNode  = getCiteRootNode(graphDb);
    }

    private Node getCiteRootNode(GraphDatabaseService graphDb) {
        Relationship rel = graphDb.getReferenceNode().getSingleRelationship(REF_CITES, Direction.OUTGOING );

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

    public Cite createCite(String text, Originator originator) throws Exception {
        /*
         * To guard against duplications we use the lock grabbed on ref node
         * when creating a relationship and are optimistic about person not existing.
         */
        Transaction tx = graphDb.beginTx();

        try {
            Node newCiteNode = graphDb.createNode();
            citeRefNode.createRelationshipTo(newCiteNode, A_CITE);
            // lock now taken, we can check if  already exist in index
            Node alreadyExist = index.get(Cite.TEXT, text).getSingle();

            if (alreadyExist != null) {
                tx.failure();
                throw new Exception("Cite with this text already exists!");
            }

            String id = UUID.randomUUID().toString();
            newCiteNode.setProperty(Cite.TEXT, text);
            newCiteNode.setProperty(Cite.ID, id);
            index.add(newCiteNode, Cite.ID, id);
            tx.success();
            return new Cite(newCiteNode);
        } finally {
            tx.finish();
        }

    }

    public Cite getCiteById(String id) {
        Node citeNode = index.get(Cite.ID, id).getSingle();

        if (null == citeNode) {
            throw new IllegalArgumentException( "Cite[" + id + "] not found!");
        }

        return new Cite(citeNode);
    }

    public void deleteCite(Cite cite) {
        Transaction tx = graphDb.beginTx();

        try {
            Node citeNode = cite.getUnderlyingNode();
            index.remove(citeNode, Cite.ID, cite.getId());
            citeNode.getSingleRelationship(A_CITE, Direction.INCOMING).delete();
            // @todo remove originator, if last cite of her.
            citeNode.delete();
            tx.success();
        } finally {
            tx.finish();
        }
    }

    public Iterable<Cite> getAllCites() {
        return new IterableWrapper<Cite, Relationship>(citeRefNode.getRelationships(A_CITE)) {
            @Override
            protected Cite underlyingObjectToObject(Relationship citeRel) {
                return new Cite(citeRel.getEndNode());
            }
        };
    }
}
