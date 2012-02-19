package de.weltraumschaf.citer.util;

/**
*
* @author Sven Strittmatter <weltraumschaf@googlemail.com>
* @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
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
