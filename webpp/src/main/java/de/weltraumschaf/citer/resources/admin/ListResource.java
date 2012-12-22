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
import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.Originator;
import de.weltraumschaf.citer.resources.BaseResource;
import de.weltraumschaf.citer.tpl.SiteContent;
import de.weltraumschaf.citer.tpl.SiteLayout;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Path("list/")
@Produces(MediaType.TEXT_HTML)
public class ListResource extends BaseResource {

    @GET public Response indexAsHtml() {
        Response response;
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - List");
            final SiteContent content = layout.newSiteContent("admin/list.tpl");
            response = Response.ok()
                .entity(layout.render(content))
                .build();
        } catch (Exception ex) {
            response = createErrorResponse(formatError(ex));
        }

        return response;
    }

    @Path("cites")
    @GET public Response citesAsHtml() {
        Response response;
        final Iterable<Cite> cites = getCiteRepo().getAll();
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - List cites");
            final SiteContent content = layout.newSiteContent("admin/list.cites.tpl");
            content.assign("cites", Lists.newArrayList(cites));
            response = Response.ok()
                .entity(layout.render(content))
                .build();
        } catch (Exception ex) {
            response = createErrorResponse(formatError(ex));
        }

        return response;
    }

    @Path("originators")
    @GET public Response originatorsAsHtml() {
        Response response;
        final Iterable<Originator> originators = getOriginatorRepo().getAll();
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - List Originators");
            final SiteContent content = layout.newSiteContent("admin/list.originators.tpl");
            content.assign("originators", Lists.newArrayList(originators));
            response = Response.ok()
                .entity(layout.render(content))
                .build();
        } catch (Exception ex) {
            response = createErrorResponse(formatError(ex));
        }

        return response;
    }

}
