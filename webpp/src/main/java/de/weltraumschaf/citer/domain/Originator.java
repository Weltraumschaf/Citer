package de.weltraumschaf.citer.domain;

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
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

}
