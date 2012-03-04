package de.weltraumschaf.citer.domain;

import java.util.Map;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class OriginatorRepository implements Repository<Originator> {

    private final GraphDatabaseService graphDb;
    private final Index<Node> index;
    private final Node originatorRefNode;

    public OriginatorRepository(GraphDatabaseService graphDb, Index<Node> index) {
        this.graphDb      = graphDb;
        this.index        = index;
        originatorRefNode = getRootNode(graphDb);
    }

    private Node getRootNode(GraphDatabaseService graphDb) {
        return null;
    }

    @Override
    public Originator create(Map<String, Object> params) {
        return null;
    }

    @Override
    public Originator findById(String id) {
        return null;
    }

    @Override
    public void delete(Originator originator) {

    }

    @Override
    public Iterable<Originator> getAll() {
        return null;
    }
}
