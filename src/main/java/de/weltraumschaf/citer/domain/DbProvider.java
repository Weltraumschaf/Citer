package de.weltraumschaf.citer.domain;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class DbProvider {

    private static final GraphDatabaseService db = new EmbeddedGraphDatabase("/tmp/citer.db");

    public static GraphDatabaseService get() {
        return db;
    }
}
