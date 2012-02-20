package de.weltraumschaf.citer.domain;

import java.util.Set;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Author {

    private String id;
    private String name;
    private Set<Cite> works;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, id);
    }

    public JSONObject toJson() throws JSONException {
        JSONObject container = new JSONObject();
        container.put("id", id);
        container.put("name", name);
        return container;
    }

}
