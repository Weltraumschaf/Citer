/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.domain.Originator;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Path("/originator/")
@Produces(MediaType.APPLICATION_JSON)
public class OriginatorResource extends BaseResource {

    @GET public JSONArray allOriginators() {
        JSONArray originators = new JSONArray();

        for (Originator originator : getOriginatorRepo().getAll()) {
            URI uri = getUriInfo().getAbsolutePathBuilder()
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
        Originator newOriginator;

        try {
            newOriginator = getOriginatorRepo().create(params);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }

        URI uri = getUriInfo().getAbsolutePathBuilder()
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
        originator.setDateUpdated(new DateTime());
        URI uri = getUriInfo().getAbsolutePathBuilder()
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
