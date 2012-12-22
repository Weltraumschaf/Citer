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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Path("/list")
public class ListResource extends BaseResource {

    @Produces(MediaType.TEXT_HTML)
    @GET public Response indexAsHtml() {
        Response response;
        final Iterable<Cite> cites = getCiteRepo().getAll();
        try {
            final SiteLayout layout = createLayout();
            layout.setTitle("Citer Admin - List");
            final SiteContent content = layout.newSiteContent("admin/list.tpl");
            content.assign("cites", Lists.newArrayList(cites));
            response = Response.ok()
                .entity(layout.render(content))
                .build();
        } catch (Exception ex) {
            response = createErrorResponse(formatError(ex));
        }

        return response;
    }

}
