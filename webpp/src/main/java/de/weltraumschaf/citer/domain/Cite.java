package de.weltraumschaf.citer.domain;

import org.joda.time.DateTime;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Cite {

    public static final String TEXT = "text";
    public static final String ID = "id";

    private final Node underlyingNode;
    private Originator originator;
    private DateTime date;
    private Language language;

    public Cite(Node underlyingNode) {
        this.underlyingNode = underlyingNode;
    }

    public Node getUnderlyingNode() {
        return underlyingNode;
    }

    public Originator getOriginator() {
        return originator;
    }

    public void setOriginator(Originator originator) {
        this.originator = originator;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getId() {
        return (String)underlyingNode.getProperty(ID);
    }

    public void setId(String id) {
        underlyingNode.setProperty(ID, id);
    }

    public String getText() {
        return (String)underlyingNode.getProperty(TEXT);
    }

    public void setText(String text) {
        underlyingNode.setProperty(TEXT, text);
    }

    @Override
    public String toString() {
        return String.format("Cite:\nid: %s\ntext: %s\ndate: %s\ncreator: %s\n", getId(), getText(), date, originator);
    }

}
