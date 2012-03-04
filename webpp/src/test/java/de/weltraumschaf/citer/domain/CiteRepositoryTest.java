package de.weltraumschaf.citer.domain;

import de.weltraumschaf.citer.Factory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import org.neo4j.graphdb.GraphDatabaseService;
import static org.junit.Assert.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com> @license
 * http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class CiteRepositoryTest {

    private static final String TEST_DB = "target/test-db";
    private static GraphDatabaseService graphDb;

    @BeforeClass
    public static void setUp() {
        deleteFileOrDirectory(new File(TEST_DB));
        graphDb = Factory.createGraphDb(TEST_DB);
        registerShutdownHook();
    }

    @AfterClass
    public static void tearDown() {
        graphDb.shutdown();
    }

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
                graphDb.shutdown();
            }
        });
    }


    @Test public void createFindAndDeleteCite() {
        CiteRepository repo = Factory.createCiteRepo(graphDb);
        String text = "This is a test cite";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Cite.TEXT, text);
        Cite cite   = repo.create(params);
        assertEquals(text, cite.getText());
        assertNotNull(cite.getId());

        Cite found = repo.findById(cite.getId());
        assertEquals(text, found.getText());
        assertEquals(cite.getId(), found.getId());

        String id = cite.getId();
        repo.delete(cite);
        assertNull(repo.findById(id));
    }
}
