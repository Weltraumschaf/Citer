package de.weltraumschaf.citer.resources;

import com.sun.jersey.api.NotFoundException;
import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.Data;
import de.weltraumschaf.citer.domain.Factory;
import de.weltraumschaf.citer.domain.Originator;
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
@Path("/originator/")
public class Originators {

    private final Data model = Factory.getModel();
    @Context UriInfo uriInfo;

    private Originator findById(String id) {
        Originator originator = model.getOriginatorById(id);

        if (null == originator) {
            throw new NotFoundException(String.format("Can't find originator with id %s.", id));
        }

        return originator;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET public JSONArray allOriginators() {
        JSONArray originators = new JSONArray();

        for (Originator originator : model.getOriginators().values()) {
            originators.put(uriInfo.getAbsolutePath() + originator.getId());
        }

        return originators;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response create(JSONObject jsonEntity) throws JSONException {
        return Response.created(uriInfo.getAbsolutePath()).build();
    }

    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public Originator get(@PathParam("id") String id) throws JSONException {
        return findById(id);
    }

    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response update(JSONObject jsonEntity) throws JSONException {
        return Response.noContent().build();
    }

    @Path("{id}/")
    @DELETE public Response delete(@PathParam("id") String id) {
        return Response.noContent().build();
    }

    @Path("{id}/cites")
    @Consumes(MediaType.APPLICATION_JSON)
    @GET public JSONArray cites() {
        return new JSONArray();
    }
}