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
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        final Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), "/de/weltraumschaf/citer/resources/admin");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        final Writer writer = new StringWriter();

        try {
            final Template temp = cfg.getTemplate("index.tpl");
            temp.process(new Object(), writer);
            writer.flush();
        } catch (IOException ex) {
            return formatError(ex);
        } catch (TemplateException ex) {
            return formatError(ex);
        }


        return writer.toString();
    }

    private String formatError(final Exception ex) {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("<h1>%s</h1>", ex.getMessage()));
        final Writer writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        try {
            writer.flush();
        } catch (IOException ex1) {
            // Doesn't do any IO.
        }
        buffer.append(String.format("<pre>%s</pre>", writer.toString()));
        return buffer.toString();
    }
}
