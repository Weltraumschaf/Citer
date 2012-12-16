/*
 *  LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */

package de.weltraumschaf.citer;

import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.CiteRepository;
import de.weltraumschaf.citer.domain.Originator;
import de.weltraumschaf.citer.domain.OriginatorRepository;
import de.weltraumschaf.citer.domain.Repository;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class CiterRegistry {

    public static final String KEY = "citer-registry";

    private final Object lock = new Object();
    private final DbFactory dbFactory;

    private GraphDatabaseService graphDb;
    private CiteRepository citeRepo;
    private OriginatorRepository originatorRepo;

    public CiterRegistry() {
        this(new DbFactory());
    }

    public CiterRegistry(final DbFactory dbFactory) {
        this.dbFactory = dbFactory;
    }

    public void setDatabase(final GraphDatabaseService db) {
        final CiteRepository cr = dbFactory.createCiteRepo(db);
        final OriginatorRepository or = dbFactory.createOriginatorRepo(db);

        synchronized (lock) {
            graphDb = db;
            citeRepo = cr;
            originatorRepo = or;
        }
    }

    public GraphDatabaseService getDatabase() {
        synchronized (lock) {
            return graphDb;
        }
    }

    public CiteRepository getCiteRepository() {
        synchronized (lock) {
            return citeRepo;
        }
    }

    public OriginatorRepository getOriginatorRepository() {
        synchronized (lock) {
            return originatorRepo;
        }
    }

}
