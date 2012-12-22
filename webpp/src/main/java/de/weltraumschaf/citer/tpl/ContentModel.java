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

import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ContentModel {

    private final Map<String, Object> vars = Maps.newHashMap();
    private LayoutModel layoutModel;

    public void assign(final String name, final Object value) {
        if (name == null) {
            throw new NullPointerException();
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        vars.put(name, value);
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public LayoutModel getLayout() {
        return layoutModel;
    }

    public void setLayout(final LayoutModel layoutModel) {
        this.layoutModel = layoutModel;
    }

}
