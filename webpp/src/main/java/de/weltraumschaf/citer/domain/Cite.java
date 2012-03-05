package de.weltraumschaf.citer.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.joda.time.DateTime;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@XmlRootElement
public class Cite extends NodeEntity {

    public static final String TEXT = "text";

    private Originator originator = new Originator(null);
    private DateTime date;
    private Language language;

    public Cite(Node underlyingNode) {
        super(underlyingNode);
    }

    public Originator getOriginator() {
        return originator;
    }

    public void setOriginator(Originator originator) {
        this.originator = originator;
    }

    @XmlTransient
    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getText() {
        return (String)getUnderlyingNode().getProperty(TEXT);
    }

    public void setText(String text) {
        getUnderlyingNode().setProperty(TEXT, text);
    }

    @Override
    public String toString() {
        return String.format("Cite:\nid: %s\ntext: %s\ndate: %s\ncreator: %s\n", getId(), getText(), date, originator);
    }

}
