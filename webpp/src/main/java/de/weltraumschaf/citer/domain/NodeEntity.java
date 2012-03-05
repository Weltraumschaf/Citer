package de.weltraumschaf.citer.domain;

import javax.xml.bind.annotation.XmlTransient;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

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
        return (String)getProperty(ID);
    }

    public void setId(String id) {
        setProperty(ID, id);
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

}
