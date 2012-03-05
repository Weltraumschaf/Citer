package de.weltraumschaf.citer.domain;

import javax.xml.bind.annotation.XmlTransient;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
abstract public class NodeEntity {

    public static final String ID = "id";

    private final Node underlyingNode;

    public NodeEntity(Node underlyingNode) {
        this.underlyingNode = underlyingNode;
    }

    @XmlTransient
    public Node getUnderlyingNode() {
        return underlyingNode;
    }

    public String getId() {
        return (String)underlyingNode.getProperty(ID);
    }

    public void setId(String id) {
        underlyingNode.setProperty(ID, id);
    }

}
