package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.domain.Originator;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
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
@Produces(MediaType.APPLICATION_JSON)
public class Originators extends BaseResource {

    @Context UriInfo uriInfo;

    @GET public JSONArray allOriginators() {
        JSONArray originators = new JSONArray();

        for (Originator originator : getOriginatorRepo().getAll()) {
            URI uri = uriInfo.getAbsolutePathBuilder()
                             .path(originator.getId())
                             .build();
            originators.put(uri.toString());
        }

        return originators;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response create(JSONObject jsonEntity) throws JSONException {
        if (!jsonEntity.has(Originator.NAME)) {
            raiseMissingPropertyError(Originator.NAME);
        }

        String name = jsonEntity.getString(Originator.NAME);
        Map params  = new HashMap<String, Object>();
        params.put(Originator.NAME, name);
        String now = now().toString();
        params.put(Originator.DATE_CREATED, now);
        params.put(Originator.DATE_UPDATED, now);
        Originator newOriginator = getOriginatorRepo().create(params);

        URI uri = uriInfo.getAbsolutePathBuilder()
                         .path(newOriginator.getId())
                         .build();
        return Response.created(uri)
                       .entity(newOriginator)
                       .build();
    }

    @Path("{id}/")
    @GET public Originator get(@PathParam("id") String id) throws JSONException {
        Originator originator = getOriginatorRepo().findById(id);

        if (null == originator) {
            raiseIdNotFoundError("originator", id);
        }

        return originator;
    }

    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response update(@PathParam("id") String id, JSONObject jsonEntity) throws JSONException {
        if (!jsonEntity.has(Originator.NAME)) {
            raiseMissingPropertyError(Originator.NAME);
        }

        String name = jsonEntity.getString(Originator.NAME);

        Originator originator = getOriginatorRepo().findById(id);

        if (null == originator) {
            raiseIdNotFoundError("originator", id);
        }

        originator.setName(name);
        originator.setDateUpdated(now());
        URI uri = uriInfo.getAbsolutePathBuilder()
                         .path(originator.getId())
                         .build();
        return Response.created(uri)
                       .entity(originator)
                       .build();
    }

    @Path("{id}/")
    @DELETE public Response delete(@PathParam("id") String id) {
        Originator originator = getOriginatorRepo().findById(id);

        if (null == originator) {
            raiseIdNotFoundError("originator", id);
        }

        getOriginatorRepo().delete(originator);
        return Response.noContent()
                       .build();
    }

    @Path("{id}/cites")
    @Consumes(MediaType.APPLICATION_JSON)
    @GET public JSONArray cites() {
        return new JSONArray();
    }
}
