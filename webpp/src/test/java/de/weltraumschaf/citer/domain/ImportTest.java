package de.weltraumschaf.citer.domain;

import java.io.IOException;
import java.net.URISyntaxException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class ImportTest {

    @Test
    public void foo() throws URISyntaxException, IOException {
        Data data = Import.createModel("/cites.json");
        assertEquals(8, data.getCites().size());
        assertEquals(8, data.getOriginators().size());
    }
}