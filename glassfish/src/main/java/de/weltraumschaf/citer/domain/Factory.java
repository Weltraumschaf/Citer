package de.weltraumschaf.citer.domain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Factory {

    private static Data model = null;

    public static Data getModel() {
        if (null == model) {
            try {
                Data data = Import.createModel("/cites.json");
                setModel(data);
            } catch (URISyntaxException ex) {
                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return model;
    }

    public static void setModel(Data m) {
        model = m;
    }
}
