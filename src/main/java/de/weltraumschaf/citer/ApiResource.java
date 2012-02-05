package de.weltraumschaf.citer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@Path("/test") public class ApiResource {

    @Produces("text/html")
    @GET public String foo() {
        return "helo";
    }
}
