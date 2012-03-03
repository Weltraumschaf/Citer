package de.weltraumschaf.citer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 * Implements a servlet context listener.
 *
 * Creates and provides an instance of a Neo4j database.
 * 
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class CiterContextListener implements ServletContextListener {

    private static final String DB_PATH = "/tmp/citer.db";
    public static final String DB = "db";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        GraphDatabaseService db = new EmbeddedGraphDatabase(DB_PATH);
        sce.getServletContext().setAttribute(DB, db);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        GraphDatabaseService db = (GraphDatabaseService)sce.getServletContext().getAttribute(DB);
        db.shutdown();
    }

}
