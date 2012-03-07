package de.weltraumschaf.citer.domain;

import de.weltraumschaf.citer.Factory;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
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
        db = Factory.createGraphDb(DB_FILE_PATH);
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
