package de.weltraumschaf.citer.domain;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class OriginatorRepository {

    private final GraphDatabaseService graphDb;
    private final Index<Node> index;
    private final Node originatorRefNode;

    public OriginatorRepository(GraphDatabaseService graphDb, Index<Node> index) {
        this.graphDb      = graphDb;
        this.index        = index;
        originatorRefNode = getOriginatorRootNode(graphDb);
    }

    private Node getOriginatorRootNode(GraphDatabaseService graphDb) {
        return null;
    }

    public Originator createCite(String name) throws Exception {
        return null;
    }

    public Originator getOriginatorById(String id) {
        return null;
    }

    public void deleteCite(Originator originator) {

    }

    public Iterable<Originator> getAllOriginators() {
        return null;
    }
}
