package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.CiterContextListener;
import de.weltraumschaf.citer.Factory;
import de.weltraumschaf.citer.domain.CiteRepository;
import de.weltraumschaf.citer.domain.OriginatorRepository;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import org.joda.time.DateTime;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Base resource providing resources to resource classes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public abstract class BaseResource {

    private static final String CITES_INDEX = "cites";
    private static final String ORIGINATOR_INDEX = "originators";

    @Context ServletConfig config;

    /**
     * Getter for neo4j graph database.
     *
     * @return Returns a neo4j embedded graph database.
     */
    protected GraphDatabaseService getGraphDb() {
        ServletContext context = config.getServletContext();
        return (GraphDatabaseService)context.getAttribute(CiterContextListener.DB);
    }

    /**
     * Getter for the cite domain repository.
     *
     * @return Returns a repository object to deal with cites.
     */
    protected CiteRepository getCiteRepo() {
        return Factory.createCiteRepo(getGraphDb());
    }

    /**
     * Getter for the cite domain repository.
     *
     * @return Returns a repository object to deal with cites.
     */
    protected OriginatorRepository getOriginatorRepo() {
        return Factory.createOriginatorRepo(getGraphDb());
    }
    
    protected DateTime now() {
        return new DateTime();
    }
}
