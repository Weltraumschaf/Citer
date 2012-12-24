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

import de.weltraumschaf.citer.tpl.SiteLayout;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import java.io.File;
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

    private static final String TEMPLATE_PREFIX = "/de/weltraumschaf/citer/resources";

    private final DbFactory dbFactory = new DbFactory();
    private final CiterRegistry registry = new CiterRegistry(dbFactory);

    public CiterContextListener() {
        super();
    }

    /**
     * File where to store the database.
     */
    private static final String DB_FILE = "citer.db";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final Config config = new Config(System.getenv("HOME") + File.separator + ".citer");
        registry.setConfig(config);
        new InstallTask(config.getHomeDir()).execute();

        registry.setDatabase(dbFactory.createGraphDb(config.getHomeDir() + File.separator + DB_FILE));

        final Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(SiteLayout.class, TEMPLATE_PREFIX);
        cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        registry.setTemplateConfig(cfg);

        sce.getServletContext().setAttribute(CiterRegistry.KEY, registry);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        final GraphDatabaseService db = registry.getDatabase();

        if (null != db) {
            db.shutdown();
        }
    }

}
