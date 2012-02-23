package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.Data;
import de.weltraumschaf.citer.domain.Factory;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class RandomCite {

    private final Data model = Factory.getModel();

    @Produces(MediaType.TEXT_HTML)
    @GET public String html() {
        Cite cite = model.getRandomCite();
        // @todo convert umls
        return String.format("<cite>%s</cite> (%s)", cite.getText(), cite.getOriginator().getName());
    }

    @Produces(MediaType.TEXT_PLAIN)
    @GET public String plainText() {
        Cite cite = model.getRandomCite();
        return String.format("\"%s\" (%s)", cite.getText(), cite.getOriginator().getName());
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET public Cite json() throws JSONException {
        return model.getRandomCite();
    }
}
