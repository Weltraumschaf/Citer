package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.util.Html5;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@Path("/") public class IndexResource {

    @Context ServletConfig config;
    @Context UriInfo uriInfo;

    @Produces(MediaType.TEXT_PLAIN)
    @GET public String indexAsPlain() {
        StringBuilder links = new StringBuilder();
        links.append(uriInfo.getBaseUri().toString()).append("randomcite").append('\n');
        links.append(uriInfo.getBaseUri().toString()).append("test");
        return links.toString();
    }

    @Produces(MediaType.TEXT_HTML)
    @GET public String indexAsHtml() {
        String title = "Index";
        Html5 html = new Html5(title);
        html.append("<h1>").append(title).append("</h1>");
        html.append("<ul>");
        html.append(String.format("<li><a href=\"%s%s\">", uriInfo.getBaseUri().toString(), "randomcite"))
            .append("randomcite").append("</a></li>");
        html.append(String.format("<li><a href=\"%s%s\">", uriInfo.getBaseUri().toString(), "test"))
            .append("test").append("</a></li>");
        html.append("</ul>");
        return html.toString();
    }

    @Produces(MediaType.TEXT_HTML)
    @Path("test")
    @GET public String test() {
        String title = "Testing around";
        Html5 html = new Html5(title);
        html.append("<h1>").append(title).append("</h1>");
        html.append("<h2>Servlet ").append(config.getServletName()).append("</h2>");

        html.append("<h3>UriInfo</h3>");
        html.append("<pre>");
        html.append("baseUri: ").append(uriInfo.getBaseUri().toString()).append("\n");
        html.append("absolutePath: ").append(uriInfo.getAbsolutePath().toString()).append("\n");
        html.append("path: ").append(uriInfo.getPath()).append("\n");
        html.append("</pre>");

        html.append("<h3>Init Pramaters</h3>");
        html.append("<pre>");
        Enumeration<String> initParamNames = config.getInitParameterNames();

        while (initParamNames.hasMoreElements()) {
            String name = initParamNames.nextElement();
            html.append(name).append(": ").append(config.getInitParameter(name)).append("\n");
        }

        html.append("</pre>");

        ServletContext context = config.getServletContext();
        Enumeration<String> contextAtrributeNames = context.getAttributeNames();
        html.append("<h3>Serlvlet Context of ")
            .append(context.getServerInfo()).append("</h3>");
        html.append("<pre>");

        while (contextAtrributeNames.hasMoreElements()) {
            String name = contextAtrributeNames.nextElement();
            html.append(name).append(": ");

            try {
                html.append(context.getAttribute(name).toString());
            } catch (NullPointerException ex) {
                html.append("null");
            }

            html.append("\n");
        }

        html.append("</pre>");
        return html.toString();
    }
}
