package de.weltraumschaf.citer.domain;

import de.weltraumschaf.citer.Factory;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com> @license
 * http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class CiteRepositoryTest extends NeoBase {

    @BeforeClass
    public static void setUp() {
        startDb();
    }

    @AfterClass
    public static void tearDown() {
        stopDb();
    }

    @Test public void createFindAndDeleteCite() {
        CiteRepository repo = Factory.createCiteRepo(db());
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
