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

import de.weltraumschaf.citer.resources.BaseResource;
import de.weltraumschaf.citer.tpl.SiteContent;
import de.weltraumschaf.citer.tpl.SiteLayout;
import freemarker.template.TemplateException;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Path("/")
public class IndexResource extends BaseResource {

    @Produces(MediaType.TEXT_HTML)
    @GET
    public String indexAsHtml() {
        try {
            final SiteLayout layout = createLayout("layout.tpl");
            layout.setTitle("Citer Admin - Index");
            final String baseUri = getUriInfo().getBaseUri().toString();
            layout.setBaseUri(baseUri);
            layout.setFaviconUri(baseUri + "../img/favicon.ico");
//            throw new IOException("foobar");
            final SiteContent content = layout.newSiteContent("admin/index.tpl");
            return layout.render(content);
        } catch (IOException ex) {
            return createErrorResponse(formatError(ex)).toString();
        } catch (TemplateException ex) {
            return createErrorResponse(formatError(ex)).toString();
        }
    }
}
