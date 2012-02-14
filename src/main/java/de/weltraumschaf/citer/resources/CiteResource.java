package de.weltraumschaf.citer.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@Path("/cite")
public class CiteResource {

    @Produces("application/json")
    @GET public String allCites() {
        return "{}";
    }

    @Path("/{id}")
    @Produces("application/json")
    @GET public String citeById() {
        return "{}";
    }
}
