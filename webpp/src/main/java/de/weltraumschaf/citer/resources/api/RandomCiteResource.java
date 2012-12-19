/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.citer.resources.api;

import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.resources.BaseResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringEscapeUtils;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Path("/cite/random/")
public class RandomCiteResource extends BaseResource {

    private final Random randomGenerator = new Random();

    public Cite getRandomCite() {
        // @todo Return collection instead of iterator.
        Iterable<Cite> cites = getCiteRepo().getAll();
        List<Cite> l = new ArrayList<Cite>();

        for (Cite c : cites){
            l.add(c);
        }

		if (l.isEmpty()) {
			return null;
		}

        int randomInt = randomGenerator.nextInt(l.size());
        return l.get(randomInt);
    }

    @Produces(MediaType.TEXT_HTML)
    @GET public String html() {
        Cite cite = getRandomCite();

		if (null == cite) {
			return "";
		}

        String text = StringEscapeUtils.escapeHtml4(cite.getText());
        String name = StringEscapeUtils.escapeHtml4(cite.getOriginator().getName());
        return String.format("<cite>%s</cite> (%s)", text, name);
    }

    @Produces(MediaType.TEXT_PLAIN)
    @GET public String plainText() {
        Cite cite = getRandomCite();

		if (null == cite) {
			return "";
		}

        return String.format("\"%s\" (%s)", cite.getText(), cite.getOriginator().getName());
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET public Cite json() throws JSONException {
        return getRandomCite();
    }
}
