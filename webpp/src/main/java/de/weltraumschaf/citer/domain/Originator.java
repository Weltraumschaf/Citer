package de.weltraumschaf.citer.domain;

import java.util.Set;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Originator {

    public static final String NAME = "name";
    public static final String ID = "id";

    private final Node underlyingNode;
    private Set<Cite> cites;

    public Originator(Node underlyingNode) {
        this.underlyingNode = underlyingNode;
    }

    public Node getUnderlyingNode() {
        return underlyingNode;
    }

    public String getId() {
        return (String)underlyingNode.getProperty(ID);
    }

    public void setId(String id) {
        underlyingNode.setProperty(ID, id);
    }

    public String getName() {
        return (String)underlyingNode.getProperty(NAME);
    }

    public void setName(String name) {
        underlyingNode.setProperty(NAME, name);
    }

    public Set<Cite> getCites() {
        return cites;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", getName(), getId());
    }

}
