package de.weltraumschaf.citer;

import de.weltraumschaf.citer.domain.CiteRepository;
import de.weltraumschaf.citer.domain.OriginatorRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Factory {

    private static final String CITES_BY_ID = "citesById";
    private static final String ORIGINATOR_BY_ID = "originatorsById";
    private static final String ORIGINATOR_BY_NAME = "originatorsByName";

    public static GraphDatabaseService createGraphDb(String path) {
        return new EmbeddedGraphDatabase(path);
    }

    public static Index<Node> createNodeIndex(GraphDatabaseService db, String name) {
        return db.index().forNodes(name);
    }

    public static CiteRepository createCiteRepo(GraphDatabaseService db) {
        return new CiteRepository(db, createNodeIndex(db, CITES_BY_ID));
    }

    public static OriginatorRepository createOriginatorRepo(GraphDatabaseService db) {
        return new OriginatorRepository(
            db,
            createNodeIndex(db, ORIGINATOR_BY_ID),
            createNodeIndex(db, ORIGINATOR_BY_NAME)
        );
    }
}
