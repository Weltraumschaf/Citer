package de.weltraumschaf.citer.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@XmlRootElement
public class Cite extends NodeEntity {

    public static final String TEXT = "text";

    private Originator originator;
    private Language language;

    public Cite(Node underlyingNode) {
        super(underlyingNode);
    }

	@XmlTransient
    public Originator getOriginator() {
        return originator;
    }

    public void setOriginator(Originator originator) {
        this.originator = originator;
    }

    public String getText() {
        return (String)getProperty(TEXT);
    }

    public void setText(String text) {
        setProperty(TEXT, text);
    }

}
