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
package de.weltraumschaf.citer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Implements a servlet context listener.
 *
 * Creates and provides an instance of a Neo4j database.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CiterContextListener implements ServletContextListener {

    /**
     * File where to store the database.
     */
    private static final String DB_PATH = "/tmp/citer.db";

    /**
     * Servlet context attribute key for the database.
     */
    public static final String DB = "db";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(DB, Factory.createGraphDb(DB_PATH));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        GraphDatabaseService db = (GraphDatabaseService)sce.getServletContext().getAttribute(DB);
        db.shutdown();
    }

}
