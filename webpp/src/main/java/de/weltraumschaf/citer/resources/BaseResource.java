package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.CiterContextListener;
import de.weltraumschaf.citer.domain.CiteRepository;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public abstract class BaseResource {

    @Context ServletConfig config;

    protected GraphDatabaseService getGraphDb() {
        ServletContext context = config.getServletContext();
        return (GraphDatabaseService)context.getAttribute(CiterContextListener.DB);
    }

    protected CiteRepository getCiteRepo() {
        return new CiteRepository(getGraphDb(), null);
    }
}
