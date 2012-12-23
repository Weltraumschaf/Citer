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
package de.weltraumschaf.citer.resources;

import com.sun.jersey.api.NotFoundException;
import de.weltraumschaf.citer.CiterRegistry;
import de.weltraumschaf.citer.domain.CiteRepository;
import de.weltraumschaf.citer.domain.OriginatorRepository;
import de.weltraumschaf.citer.tpl.SiteLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.ServletConfig;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Base resource providing resources to resource classes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class BaseResource {

    private ServletConfig servletConfig;
    private UriInfo uriInfo;

    public BaseResource() {
        super();
    }

    public SiteLayout createLayout() throws IOException {
        return createLayout("layout.tpl");
    }

    public SiteLayout createLayout(final String layoutFile) throws IOException {
        final SiteLayout layout = SiteLayout.newLayout(layoutFile);
        final String baseUri = getUriInfo().getBaseUri().toString();
        layout.setBaseUri(baseUri);
        layout.setFaviconUri(baseUri + "../img/favicon.ico");
        layout.addCssUri(baseUri + "../css/admin/main.css");
        layout.addJsUri(baseUri + "../js/jquery.js");
        layout.addJsUri(baseUri + "../js/admin/main.js");
        return layout;
    }

    public ServletConfig getConfig() {
        return servletConfig;
    }

    @Context
    public void setConfig(ServletConfig config) {
        this.servletConfig = config;
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }

    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    protected CiterRegistry getRegistry() {
        return (CiterRegistry) servletConfig.getServletContext().getAttribute(CiterRegistry.KEY);
    }

    /**
     * Getter for neo4j graph database.
     *
     * @return Returns a neo4j embedded graph database.
     */
    protected GraphDatabaseService getGraphDb() {
        return getRegistry().getDatabase();
    }

    /**
     * Getter for the cite domain repository.
     *
     * @return Returns a repository object to deal with cites.
     */
    protected CiteRepository getCiteRepo() {
        return (CiteRepository) getRegistry().getCiteRepository();
    }

    /**
     * Getter for the cite domain repository.
     *
     * @return Returns a repository object to deal with cites.
     */
    protected OriginatorRepository getOriginatorRepo() {
        return (OriginatorRepository) getRegistry().getOriginatorRepository();
    }

    protected Response createErrorResponse(String message) {
        return Response.serverError()
                .entity(message)
                .build();
    }

    protected void raiseIdNotFoundError(String resource, String id) throws WebApplicationException {
        String message = String.format("Can't find '%s' with id '%s'!", resource, id);
        throw new NotFoundException(message);
    }

    protected void raiseMissingPropertyError(String name) throws WebApplicationException {
        String message = String.format("Property '%s' missing!", name);
        throw new WebApplicationException(createErrorResponse(message));
    }

    protected String formatUriInfo() {
        return formatUriInfo(getUriInfo());
    }

    protected String formatUriInfo(UriInfo info) {
        final String FORMAT = "%s: %s%n";
        final StringBuilder buffer = new StringBuilder();
        buffer.append("<pre>");
        buffer.append(String.format(FORMAT, "Path", info.getPath()));
        buffer.append(String.format(FORMAT, "BaseUri", info.getBaseUri().toString()));
        buffer.append(String.format(FORMAT, "AbsolutePath", info.getAbsolutePath().toString()));
        return buffer.toString();
    }

    protected String formatError(final Exception ex) {
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

    protected Response stringOkResponse(final String entity) {
        Response response;
        response = Response.ok()
            .entity(entity)
            .build();
        return response;
    }

}
