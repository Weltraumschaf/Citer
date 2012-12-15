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

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@XmlRootElement
public class Originator extends NodeEntity {

    public static final String NAME = "name";

    private Set<Cite> cites;

    public Originator(Node underlyingNode) {
        super(underlyingNode);
    }

    public String getName() {
        return (String)getProperty(NAME);
    }

    public void setName(String name) {
        setProperty(NAME, name);
    }

    @XmlTransient
    public Set<Cite> getCites() {
        return cites;
    }

    @Override
    public String toString() {
        return String.format("Originator[%s, %s]", getId(), getName());
    }
}
