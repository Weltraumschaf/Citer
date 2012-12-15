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

import org.neo4j.graphdb.RelationshipType;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum RelTypes implements RelationshipType {
    REF_CITES,
    REF_ORIGINATORS,
    A_CITE,
    A_ORIGINATOR,
    CREATED_BY
}
