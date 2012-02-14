package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.Data;
import de.weltraumschaf.citer.domain.Factory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@Path("/randomcite") public class RandomCiteResource {

    private final Data model = Factory.getModel();

    @Produces("text/html")
    @GET public String html() {
        Cite cite = model.getRandomCite();
        return String.format("<cite>%s (%s)</cite>", cite.getText(), cite.getCrator().getName());
    }

    @Produces("text/plain")
    @GET public String plainText() {
        Cite cite = model.getRandomCite();
        return String.format("%s (%s)", cite.getText(), cite.getCrator().getName());
    }

    @Produces("application/json")
    @GET public String json() {
        JSONObject container = new JSONObject();
        container.put("cite", model.getRandomCite().toJson());
        return container.toJSONString();
    }
}
