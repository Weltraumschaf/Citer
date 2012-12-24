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
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SiteLayout {


    private final LayoutModel model = new LayoutModel();
    private final Configuration config;
    private final Template layout;

    public SiteLayout(final Configuration cfg, final String layoutFile) throws IOException {
        super();
        config = cfg;
        layout = cfg.getTemplate(layoutFile);
    }

    public void setTitle(final String title) {
        model.setTitle(title);
    }

    public void setBaseUri(final String baseUri) {
        model.setBaseUri(baseUri);
    }

    public void addJsUri(final String javaScriptUri) {
        model.addJsUri(javaScriptUri);
    }

    public void addCssUri(final String cssUri) {
        model.addCssUri(cssUri);
    }

    public void setFaviconUri(final String faviconUri) {
        model.setFaviconUri(faviconUri);
    }

    public SiteContent newSiteContent(final String templateFile) throws IOException {
        final SiteContent siteContent = new SiteContent(config, templateFile);
        siteContent.getModel().setLayout(model);
        return siteContent;
    }

    public String render(final SiteContent content) throws TemplateException, IOException {
        model.setContent(content.render());
        final Writer writer = new StringWriter();
        layout.process(model, writer);
        writer.flush();
        return writer.toString();
    }
}
