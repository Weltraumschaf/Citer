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
package de.weltraumschaf.citer.domain;

import de.weltraumschaf.citer.DbFactory;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
abstract public class NeoBase {

    private static final String DB_FILE_PATH = "target/test-db";
    private static GraphDatabaseService db;

    private static void deleteFileOrDirectory(final File file) {
        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteFileOrDirectory(child);
            }
        } else {
            file.delete();
        }
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                db.shutdown();
            }
        });
    }

    @BeforeClass
    public static void startDb() {
        deleteFileOrDirectory(new File(DB_FILE_PATH));
        db = new DbFactory().createGraphDb(DB_FILE_PATH);
        registerShutdownHook();
    }

    @AfterClass
    public static void stopDb() {
        db.shutdown();
    }

    protected static GraphDatabaseService db() {
        return db;
    }
}
