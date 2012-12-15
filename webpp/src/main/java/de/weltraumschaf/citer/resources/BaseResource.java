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
import de.weltraumschaf.citer.CiterContextListener;
import de.weltraumschaf.citer.Factory;
import de.weltraumschaf.citer.domain.CiteRepository;
import de.weltraumschaf.citer.domain.OriginatorRepository;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

    private ServletConfig config;
    private UriInfo uriInfo;
    private CiteRepository citeRepo;
    private OriginatorRepository originatorRepo;

    public ServletConfig getConfig() {
        return config;
    }

    @Context
    public void setConfig(ServletConfig config) {
        this.config = config;
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }

    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    /**
     * Getter for neo4j graph database.
     *
     * @return Returns a neo4j embedded graph database.
     */
    protected GraphDatabaseService getGraphDb() {
        ServletContext context = config.getServletContext();
        GraphDatabaseService db = (GraphDatabaseService)context.getAttribute(CiterContextListener.DB);

        if (null == db) {
            throw new RuntimeException("Can't obtain graph db from servlet context!");
        }

        return db;
    }

    /**
     * Getter for the cite domain repository.
     *
     * @return Returns a repository object to deal with cites.
     */
    protected CiteRepository getCiteRepo() {
        if (null == citeRepo) {
            citeRepo = Factory.createCiteRepo(getGraphDb());
        }

        return citeRepo;
    }

    /**
     * Getter for the cite domain repository.
     *
     * @return Returns a repository object to deal with cites.
     */
    protected OriginatorRepository getOriginatorRepo() {
        if (null == originatorRepo) {
            originatorRepo = Factory.createOriginatorRepo(getGraphDb());
        }

        return originatorRepo;
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
}
