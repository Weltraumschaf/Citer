/*
 *  LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */

package de.weltraumschaf.citer.resources.admin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.Originator;
import de.weltraumschaf.citer.resources.BaseResource;
import de.weltraumschaf.citer.tpl.SiteContent;
import de.weltraumschaf.citer.tpl.SiteLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Path("/cite/")
public class CiteResource extends BaseResource {


    @Produces(MediaType.TEXT_HTML)
    @GET public Response index() {
        Response response;
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - Cite");
            final SiteContent content = layout.newSiteContent("admin/cite.tpl");
            response = Response.ok()
                .entity(layout.render(content))
                .build();
        } catch (Exception ex) {
            response = createErrorResponse(formatError(ex));
        }

        return response;
    }

    @Path("generate/{count}")
    @Produces(MediaType.TEXT_HTML)
    @GET public String originator(@PathParam("count") String count) {
        final int cnt = Integer.valueOf(count);

        try {
            final Iterable<Cite> cites = createCites(cnt);
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - Cite - Create random cites");
            final SiteContent content = layout.newSiteContent("admin/cite.generate.tpl");
            content.assign("cites", cites);
            return layout.render(content);
        } catch (Exception ex) {
            return createErrorResponse(formatError(ex)).toString();
        }
    }

    private Iterable<Cite> createCites(int cnt) throws Exception {
        final List<Originator> originators = createOriginators();
        final List<Cite> cites = Lists.newArrayList();

        for (int i = 0; i < cnt; ++i) {
            final Cite cite = getCiteRepo().create(createRandomParams(originators));
            cites.add(cite);
        }

        return cites;
    }

    private Map<String, Object> createRandomParams(final List<Originator> originators) {
        final Map<String, Object> params  = new HashMap<String, Object>();
        params.put(Cite.TEXT, createText());
        Cite newCite = getCiteRepo().create(params);
        newCite.setOriginator(getRandomOriginator(originators));
        return params;
    }

    private static final List<String> NAMES = Lists.newArrayList("Hans Dampf", "Bernd Müller", "Hildegard Munzinger");

    private List<Originator> createOriginators() throws Exception {
        final List<Originator> originators = Lists.newArrayList();
        for (final String name : NAMES) {
            final Map<String, Object> originatorParams  = Maps.newHashMap();
            originatorParams.put(Originator.NAME, name);
            originators.add(getOriginatorRepo().create(originatorParams));
        }
        return originators;
    }

    private String createText() {
        return "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam";
    }

    private final Random rand = new Random();

    private Originator getRandomOriginator(List<Originator> originators) {
        final int index = rand.nextInt(originators.size());
        return originators.get(index);
    }
}
