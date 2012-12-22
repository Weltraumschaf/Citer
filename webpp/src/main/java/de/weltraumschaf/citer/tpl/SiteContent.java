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

package de.weltraumschaf.citer.tpl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SiteContent {

    private final Template template;
    private final ContentModel model = new ContentModel();

    public SiteContent(final Configuration cfg, final String template) throws IOException {
        this(cfg.getTemplate(template));
    }
    public SiteContent(final Template template) {
        this.template = template;
    }

    public String render() throws TemplateException, IOException {
        final Writer writer = new StringWriter();
        template.process(model, writer);
        writer.flush();
        return writer.toString();
    }

    ContentModel getModel() {
        return model;
    }

    public void assign(final String name, final Object value) {
        model.assign(name, value);
    }

}
