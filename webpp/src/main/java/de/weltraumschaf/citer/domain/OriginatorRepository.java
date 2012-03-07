package de.weltraumschaf.citer.domain;

import static de.weltraumschaf.citer.domain.RelTypes.A_ORIGINATOR;
import static de.weltraumschaf.citer.domain.RelTypes.REF_ORIGINATORS;
import java.util.Map;
import java.util.UUID;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.index.Index;
import org.neo4j.helpers.collection.IterableWrapper;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
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

        try {
            Node newOriginatorNode = graphDb.createNode();
            referenceNode.createRelationshipTo(newOriginatorNode, A_ORIGINATOR);
            String name = (String)params.get(Originator.NAME);
            // lock now taken, we can check if  already exist in index
            Node alreadyExist = indexByName.get(Originator.NAME, name)
                                           .getSingle();

            if (alreadyExist != null) {
                tx.failure();
                throw new Exception(String.format("Originator with name '%s' already exists!", name));
            }

            for (String paramName : params.keySet()) {
                newOriginatorNode.setProperty(paramName, params.get(paramName));
            }

            String id = UUID.randomUUID().toString();
            newOriginatorNode.setProperty(Originator.ID, id);
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
