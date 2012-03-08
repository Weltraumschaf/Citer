package de.weltraumschaf.citer.domain;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.neo4j.graphdb.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@XmlRootElement
public class Cite extends NodeEntity {

    public static final String TEXT = "text";

    // @todo Implement languages
//    private Language language;

    public Cite(Node underlyingNode) {
        super(underlyingNode);
    }

	@XmlTransient
    public Originator getOriginator() {
        Traverser traverser =  getUnderlyingNode().traverse(
            Traverser.Order.BREADTH_FIRST,
            StopEvaluator.END_OF_GRAPH,
            ReturnableEvaluator.ALL_BUT_START_NODE, RelTypes.CREATED_BY,
            Direction.OUTGOING
        );

        Collection<Node> originatorNodes = traverser.getAllNodes();

        if (originatorNodes.isEmpty()) {
            return null;
        }

        if (originatorNodes.size() > 1) {
            throw new RuntimeException("Insufficant number of originators: " + originatorNodes.size() + "!");
        }

        Node originatorNode = originatorNodes.iterator().next();
        return new Originator(originatorNode);
    }

    public void setOriginator(Originator originator) {
        getUnderlyingNode().createRelationshipTo(originator.getUnderlyingNode(), RelTypes.CREATED_BY);
    }

    public String getText() {
        return (String)getProperty(TEXT);
    }

    public void setText(String text) {
        setProperty(TEXT, text);
    }

    @Override
    public String toString() {
        String text = getText();

        if (text.length() > 15) {
            text = text.substring(0, 15);
        }

        return String.format("Cite[%s, %s, %s]", getId(), text, getOriginator().getName());
    }
}
