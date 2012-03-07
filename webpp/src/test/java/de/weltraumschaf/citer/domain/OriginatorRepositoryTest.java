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
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class OriginatorRepositoryTest extends NeoBase {

    @BeforeClass
    public static void setUp() {
        startDb();
    }

    @AfterClass
    public static void tearDown() {
        stopDb();
    }

    @Test public void createFindAndDeleteOriginator() throws Exception {
        OriginatorRepository repo = Factory.createOriginatorRepo(db());
        String name = "Sven Strittmatter";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Originator.NAME, name);
        Originator originator = repo.create(params);
        assertEquals(name, originator.getName());
        assertNotNull(originator.getId());

        Originator found = repo.findById(originator.getId());
        assertEquals(name, found.getName());
        assertEquals(originator.getId(), found.getId());

        String id = originator.getId();
        repo.delete(originator);
        assertNull(repo.findById(id));
    }

    @Test public void findByName() throws Exception {
        OriginatorRepository repo = Factory.createOriginatorRepo(db());
        String name = "Sven Strittmatter";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Originator.NAME, name);
        assertNull(repo.findByName(name));
        Originator created = repo.create(params);
        Originator found = repo.findByName(name);
        assertNotNull(found);
        assertEquals(name, found.getName());
        assertEquals(created, found);

        String name2 = "Hans Dampf";
        assertNull(repo.findByName(name2));
        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put(Originator.NAME, name2);
        repo.create(params2);
        Originator created2 = repo.create(params2);
        Originator found2 = repo.findByName(name2);
        assertNotNull(found2);
        assertEquals(name2, found2.getName());
        assertTrue(created2.equals(found2));
    }
}
