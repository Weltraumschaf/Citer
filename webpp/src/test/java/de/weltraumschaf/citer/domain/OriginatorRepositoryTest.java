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
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class OriginatorRepositoryTest extends NeoBase {

    private OriginatorRepository repo;

    @Before
    public void setUp() {
        repo = new DbFactory().createOriginatorRepo(db());
    }

    @Test public void createFindAndDeleteOriginator() throws Exception {
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

    @Test(expected=Exception.class)
    public void throwExceptionOnDuplicateName() throws Exception {
        String name = "Albert Einstein";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Originator.NAME, name);

        assertNull(repo.findByName(name));
        repo.create(params);
        assertNotNull(repo.findByName(name));
        repo.create(params);
    }

    @Test public void findByName() throws Exception {
        String name = "Bernd Brot";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Originator.NAME, name);
        assertNull(repo.findByName(name));
        Originator created = repo.create(params);
        Originator found = repo.findByName(name);
        assertNotNull(found);
        assertEquals(created, found);
        assertEquals(name, found.getName());

        String name2 = "Hans Dampf";
        assertNull(repo.findByName(name2));
        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put(Originator.NAME, name2);
        Originator created2 = repo.create(params2);
        Originator found2 = repo.findByName(name2);
        assertNotNull(found2);
        assertEquals(created2, found2);
        assertEquals(name2, found2.getName());
    }
}
