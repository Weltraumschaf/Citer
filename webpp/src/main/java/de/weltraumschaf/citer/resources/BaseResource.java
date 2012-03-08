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
import org.joda.time.DateTime;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Base resource providing resources to resource classes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public abstract class BaseResource {

    @Context ServletConfig config;

    private CiteRepository citeRepo;
    private OriginatorRepository originatorRepo;

    /**
     * Getter for neo4j graph database.
     *
     * @return Returns a neo4j embedded graph database.
     */
    protected final GraphDatabaseService getGraphDb() {
        ServletContext context = config.getServletContext();
        return (GraphDatabaseService)context.getAttribute(CiterContextListener.DB);
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
