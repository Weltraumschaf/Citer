package de.weltraumschaf.citer.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Data {

    private final List<Cite> cites = new ArrayList<Cite>();
    private final Map<String, Author> authors = new HashMap<String, Author>();

    public boolean hasAuthorWithName(String name) {
        return authors.containsKey(name);
    }

    public Author getAuthorByName(String name) {
        if (hasAuthorWithName(name)) {
            return authors.get(name);
        }

        return null;
    }

    public void addAuthor(Author author) {
        authors.put(author.getName(), author);
    }

    public Map<String, Author> getAuthors() {
        return authors;
    }

    public void addCite(Cite cite) {
        cites.add(cite);
    }

    public List<Cite> getCites() {
        return cites;
    }
}
