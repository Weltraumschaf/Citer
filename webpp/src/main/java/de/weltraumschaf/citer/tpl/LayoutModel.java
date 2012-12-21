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

import com.google.common.collect.Lists;
import java.util.List;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class LayoutModel {

    private final List<String> jsUris = Lists.newArrayList();
    private final List<String> cssUris = Lists.newArrayList();
    private String title = "";
    private String baseUri = "";
    private String content = "";
    private String faviconUri = "";

    public String getFaviconUri() {
        return faviconUri;
    }

    public void setFaviconUri(final String faviconUri) {
        this.faviconUri = faviconUri;
    }

    public void addJsUri(final String javaScriptUri) {
        jsUris.add(javaScriptUri);
    }

    public List<String> getJsUris() {
        return jsUris;
    }

    public void addCssUri(final String cssUri) {
        cssUris.add(cssUri);
    }

    public List<String> getCssUris() {
        return cssUris;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(final String baseUri) {
        this.baseUri = baseUri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}