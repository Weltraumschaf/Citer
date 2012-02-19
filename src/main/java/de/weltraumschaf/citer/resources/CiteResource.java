package de.weltraumschaf.citer.resources;


import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONArray;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@Path("/cite")
public class CiteResource {

    @Context UriInfo uriInfo;

    private List<String> citeUris() {
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        ids.add("4");
        return ids;
    }

    @Produces(MediaType.TEXT_PLAIN)
    @GET public String allCitesAsText() {
        StringBuilder uris = new StringBuilder();

        for (String id : citeUris()) {
            uris.append(uriInfo.getAbsolutePath()).append(id).append('\n');
        }

        return uris.toString();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET public String allCites() {
        JSONArray cites = new JSONArray();

        for (String id : citeUris()) {
            cites.add(uriInfo.getAbsolutePath().toString() + id);
        }

        return cites.toJSONString();
    }

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public String citeById(@PathParam("id") String id) {
        return "{}";
    }

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PUT public String create(@PathParam("id") String id) {
        if (null == id) {
            // create
        } else {
            // update
        }

        return "{}";
    }

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE public String delete(@PathParam("id") String id) {
        return "{}";
    }
}
