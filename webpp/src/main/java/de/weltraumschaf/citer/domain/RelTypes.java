package de.weltraumschaf.citer.domain;

import org.neo4j.graphdb.RelationshipType;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public enum RelTypes implements RelationshipType {
    REF_CITES,
    REF_ORIGINATORS,
    A_CITE,
    A_ORIGINATOR,
    CREATED_BY
}
