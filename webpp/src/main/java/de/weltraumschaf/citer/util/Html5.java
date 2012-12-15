/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.citer.util;

/**
*
* @author Sven Strittmatter <weltraumschaf@googlemail.com>
*/
public class Html5 {

    private final String template = "<!DOCTYPE HTML>\n"
        + "<html lang=\"en\">\n"
        + "<head>\n"
        + "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>\n"
        + "<title>%s</title>\n"
        + "</head>\n"
        + "<body>\n"
        + "%s"
        + "</body>\n"
        + "</html>";

    private final String title;
    private final StringBuilder body;

    public Html5(String title) {
        this.title = title;
        this.body  = new StringBuilder();
    }

    public Html5 append(String s) {
        body.append(s);
        return this;
    }

    @Override
    public String toString() {
        return String.format(template, title, body.toString());
    }

}
