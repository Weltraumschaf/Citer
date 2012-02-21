package de.weltraumschaf.citer.resources;

import com.sun.jersey.api.NotFoundException;
import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.Data;
import de.weltraumschaf.citer.domain.Factory;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
@Path("/cite/")
public class Cites {

    private final Data model = Factory.getModel();

    @Context UriInfo uriInfo;

    @Path("random/")
    public RandomCite randomCite() {
        return new RandomCite();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET public JSONArray allCites() {
        JSONArray cites = new JSONArray();

        for (Cite cite : model.getCites()) {
            cites.put(String.format("%s%s", uriInfo.getAbsolutePath(), cite.getId()));
        }

        return cites;
    }

    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public JSONObject citeById(@PathParam("id") String id) throws JSONException {
        Cite cite = model.getCiteById(id);

        if (null == cite) {
            throw new NotFoundException(String.format("Can't find cite with id %s.", id));
        }

        return cite.toJson();
    }

    @Consumes("application/json")
    @PUT public Response create(JSONObject jsonEntity) throws JSONException {
        if (true) {
            // create
            return Response.created(uriInfo.getAbsolutePath()).build();
        } else {
            // update
            return Response.noContent().build();
        }
    }

    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE public JSONObject delete(@PathParam("id") String id) {
        return new JSONObject();
    }
}
