package de.weltraumschaf.citer.util;

import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Environment {

    public enum Variable {
        HOME("HOME"), TMPDIR("TMPDIR"), MACONHA_HOME("MACONHA_HOME");

        private final String name;

        private Variable(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static final Map<String, String> env;

    static {
        env = System.getenv();
    }

    public boolean hasVar(Variable v) {
        return env.containsKey(v.toString());
    }

    public String getVar(Variable v) {
        return env.get(v.toString());
    }
}
