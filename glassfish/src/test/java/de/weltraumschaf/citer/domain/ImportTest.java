package de.weltraumschaf.citer.domain;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class ImportTest {

    @Test @Ignore
    public void foo() throws URISyntaxException, IOException {
        Data data = Import.createModel("/cites.json");

        for (Cite c : data.getCites()) {
            System.out.println(c.toString());
        }
    }

}