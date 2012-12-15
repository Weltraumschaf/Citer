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

import javax.xml.bind.annotation.XmlTransient;
import org.joda.time.DateTime;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
abstract public class NodeEntity {

    public static final String ID = "id";
    public static final String DATE_CREATED = "created";
    public static final String DATE_UPDATED = "updated";

    private final Node underlyingNode;

    public NodeEntity(Node underlyingNode) {
        this.underlyingNode = underlyingNode;
    }

    @XmlTransient
    public Node getUnderlyingNode() {
        return underlyingNode;
    }

    public String getId() {
        return (String)getProperty(ID);
    }

    public void setId(String id) {
        setProperty(ID, id);
    }

    public DateTime getDateCreated() {
        String date = (String)getProperty(DATE_CREATED);
        return new DateTime(date);
    }

    public void setDateCreated(DateTime date) {
        setProperty(DATE_CREATED, date.toString());
    }

    public DateTime getDateUpdated() {
        String date = (String)getProperty(DATE_UPDATED);
        return new DateTime(date);
    }

    public void setDateUpdated(DateTime date) {
        setProperty(DATE_UPDATED, date.toString());
    }

    protected void setProperty(String key, Object value) {
        Transaction tx = underlyingNode.getGraphDatabase()
                                       .beginTx();

        try {
            underlyingNode.setProperty(key, value);
            tx.success();
        } finally {
            tx.finish();
        }
    }

    protected Object getProperty(String key) {
        return underlyingNode.getProperty(key);
    }

    @Override
    public int hashCode() {
        return underlyingNode.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NodeEntity &&
            getUnderlyingNode().equals(((NodeEntity)other).getUnderlyingNode());
    }
}
