package de.weltraumschaf.citer.resources;

import com.sun.jersey.api.NotFoundException;
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
public class Originators extends BaseResource {

    @Context UriInfo uriInfo;

    @Produces(MediaType.APPLICATION_JSON)
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
        String name = jsonEntity.getString("name");
        Map params  = new HashMap<String, Object>();
        params.put(Originator.NAME, name);
        Originator newOriginator = getOriginatorRepo().create(params);

        URI uri = uriInfo.getAbsolutePathBuilder()
                         .path(newOriginator.getId())
                         .build();
        return Response.created(uri)
                       .build();
    }

    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public Originator get(@PathParam("id") String id) throws JSONException {
        Originator originator = getOriginatorRepo().findById(id);

        if (null == originator) {
            throw new NotFoundException(String.format("Can't find originator with id %s.", id));
        }

        return originator;
    }

    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response update(@PathParam("id") String id, JSONObject jsonEntity) throws JSONException {
        String name = jsonEntity.getString("name");

        Originator originator = getOriginatorRepo().findById(id);

        if (null == originator) {
            throw new NotFoundException(String.format("Can't find originator with id %s.", id));
        }

        originator.setName(name);
        URI uri = uriInfo.getAbsolutePathBuilder()
                         .path(originator.getId())
                         .build();
        return Response.created(uri)
                       .build();
    }

    @Path("{id}/")
    @DELETE public Response delete(@PathParam("id") String id) {
        Originator originator = getOriginatorRepo().findById(id);

        if (null == originator) {
            throw new NotFoundException(String.format("Can't find originator with id %s.", id));
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
