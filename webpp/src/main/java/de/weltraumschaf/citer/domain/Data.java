package de.weltraumschaf.citer.domain;

import java.util.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Data {

    private final List<Cite> cites = new ArrayList<Cite>();
    private final Map<String, Originator> originators = new HashMap<String, Originator>();
    private final Random randomGenerator = new Random();

    public boolean hasOriginatorWithName(String name) {
        return originators.containsKey(name);
    }

    public Originator getOriginatorById(String id) {
        for (Originator originator : originators.values()) {
            if (originator.getId().equals(id)) {
                return originator;
            }
        }
        return null;
    }

    public Originator getOriginatorByName(String name) {
        if (hasOriginatorWithName(name)) {
            return originators.get(name);
        }

        return null;
    }

    public void addOriginator(Originator originator) {
        originators.put(originator.getName(), originator);
    }

    public Map<String, Originator> getOriginators() {
        return originators;
    }

    public void addCite(Cite cite) {
        cites.add(cite);
    }

    public List<Cite> getCites() {
        return cites;
    }

    public Cite getRandomCite() {
        int randomInt = randomGenerator.nextInt(cites.size());
        return cites.get(randomInt);
    }

    public Cite getCiteById(String id) {
        for (Cite cite : cites) {
            if (cite.getId().equals(id)) {
                return cite;
            }
        }

        return null;
    }
}
