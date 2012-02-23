package de.weltraumschaf.citer;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class ServiceProvider {

    private static final GraphDatabaseService database = new EmbeddedGraphDatabase("/tmp/citer.db");
    private static final ThreadLocal<GraphDatabaseService> threadLocal = new ThreadLocal<GraphDatabaseService>() {

        @Override protected GraphDatabaseService initialValue() {
            return database;
        }
    };

    public static GraphDatabaseService getGraphDb() {
        return threadLocal.get();
    }
}
