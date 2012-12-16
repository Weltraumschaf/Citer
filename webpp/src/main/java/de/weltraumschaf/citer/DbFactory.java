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

import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.CiteRepository;
import de.weltraumschaf.citer.domain.NodeEntity;
import de.weltraumschaf.citer.domain.Originator;
import de.weltraumschaf.citer.domain.OriginatorRepository;
import de.weltraumschaf.citer.domain.Repository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class DbFactory {

    private static final String CITES_BY_ID = "citesById";
    private static final String ORIGINATOR_BY_ID = "originatorsById";
    private static final String ORIGINATOR_BY_NAME = "originatorsByName";

    public DbFactory() {
        super();
    }

    public GraphDatabaseService createGraphDb(final String path) {
        return new EmbeddedGraphDatabase(path);
    }

    public Index<Node> createNodeIndex(final GraphDatabaseService db, final String name) {
        return db.index().forNodes(name);
    }

    public CiteRepository createCiteRepo(final GraphDatabaseService db) {
        return new CiteRepository(db, createNodeIndex(db, CITES_BY_ID));
    }

    public OriginatorRepository createOriginatorRepo(final GraphDatabaseService db) {
        return new OriginatorRepository(
            db,
            createNodeIndex(db, ORIGINATOR_BY_ID),
            createNodeIndex(db, ORIGINATOR_BY_NAME)
        );
    }
}
