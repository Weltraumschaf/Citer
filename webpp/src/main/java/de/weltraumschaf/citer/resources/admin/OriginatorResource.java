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
import de.weltraumschaf.citer.domain.Originator;
import de.weltraumschaf.citer.resources.BaseResource;
import de.weltraumschaf.citer.tpl.SiteContent;
import de.weltraumschaf.citer.tpl.SiteLayout;
import javax.ws.rs.DELETE;
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
@Path("originator/")
public class OriginatorResource extends BaseResource {

    @Produces(MediaType.TEXT_HTML)
    @GET public Response originatorsAsHtml() {
        final Iterable<Originator> originators = getOriginatorRepo().getAll();
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - List Originators");
            final SiteContent content = layout.newSiteContent("admin/originators.tpl");
            content.assign("originators", Lists.newArrayList(originators));
            return stringOkResponse(layout.render(content));
        } catch (Exception ex) {
            return createErrorResponse(formatError(ex));
        }
    }

    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    @GET public Response originatorAsHtml(@PathParam("id") String id) {
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - Originator");
            final SiteContent content = layout.newSiteContent("admin/originator.tpl");
            content.assign("originator", getOriginatorRepo().findById(id));
            return stringOkResponse(layout.render(content));
        } catch (Exception ex) {
            return createErrorResponse(formatError(ex));
        }
    }

    @Path("edit/{id}")
    @Produces(MediaType.TEXT_HTML)
    @GET public Response editAsHtml(@PathParam("id") String id) {
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - Originator edit");
            final SiteContent content = layout.newSiteContent("admin/originator.edit.tpl");
            content.assign("actionUri", getUriInfo().getAbsolutePath().toString());
            content.assign("originator", getOriginatorRepo().findById(id));
            return stringOkResponse(layout.render(content));
        } catch (Exception ex) {
            return createErrorResponse(formatError(ex));
        }
    }

    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE public Response delete() {
        return stringOkResponse("deleted");
    }

}
