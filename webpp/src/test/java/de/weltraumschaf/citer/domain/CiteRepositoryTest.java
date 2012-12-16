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
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
public class CiteRepositoryTest extends NeoBase {

    @Ignore
    @Test public void createFindAndDeleteCite() {
        CiteRepository repo = new DbFactory().createCiteRepo(db());
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
