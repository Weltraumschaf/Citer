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
import de.weltraumschaf.citer.resources.BaseResource;
import de.weltraumschaf.citer.tpl.SiteContent;
import de.weltraumschaf.citer.tpl.SiteLayout;
import de.weltraumschaf.citer.util.Randomator;
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
@Path("cite/")
public class CiteResource extends BaseResource {

    @Produces(MediaType.TEXT_HTML)
    @GET public Response citesAsHtml() {
        final Iterable<Cite> cites = getCiteRepo().getAll();
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - List cites");
            final SiteContent content = layout.newSiteContent("admin/cite.tpl");
            content.assign("cites", Lists.newArrayList(cites));
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
            layout.setTitle("Citer Admin - Cite edit");
            final SiteContent content = layout.newSiteContent("admin/cite.edit.tpl");
            content.assign("actionUri", getUriInfo().getAbsolutePath().toString());
            content.assign("cite", getCiteRepo().findById(id));
            return stringOkResponse(layout.render(content));
        } catch (Exception ex) {
            return createErrorResponse(formatError(ex));
        }
    }

    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @DELETE public Response delete(@PathParam("id") String id) {
        try {
            final Cite cite = getCiteRepo().findById(id);
            getCiteRepo().delete(cite);
            return stringOkResponse(getUriInfo().getBaseUri().toString() + "cite/");
        } catch (Exception ex) {
            return createErrorResponse(formatError(ex));
        }
    }

    @Path("generate/{count}")
    @Produces(MediaType.TEXT_HTML)
    @GET public Response generate(@PathParam("count") String count) {
        final int cnt = Integer.valueOf(count);
        final Randomator generator = new Randomator(getCiteRepo(), getOriginatorRepo());

        try {
            final Iterable<Cite> cites = generator.createCites(cnt);
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - Created random cites");
            final SiteContent content = layout.newSiteContent("admin/cite.generate.tpl");
            content.assign("cites", cites);
            return stringOkResponse(layout.render(content));
        } catch (Exception ex) {
            return createErrorResponse(formatError(ex));
        }
    }

}
