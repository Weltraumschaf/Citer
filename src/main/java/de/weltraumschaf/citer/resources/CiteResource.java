package de.weltraumschaf.citer.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@Path("/cite")
public class CiteResource {

    @Context UriInfo uriInfo;

    @Produces(MediaType.APPLICATION_JSON)
    @GET public JSONArray allCites() {
        JSONArray cites = new JSONArray();
        return cites;
    }

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public JSONObject citeById(@PathParam("id") String id) {
        return new JSONObject();
    }

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PUT public JSONObject create(@PathParam("id") String id) {
        if (null == id) {
            // create
        } else {
            // update
        }

        return new JSONObject();
    }

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE public JSONObject delete(@PathParam("id") String id) {
        return new JSONObject();
    }
}
