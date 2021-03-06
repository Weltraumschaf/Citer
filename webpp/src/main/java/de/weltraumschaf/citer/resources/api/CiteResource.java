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
package de.weltraumschaf.citer.resources.api;

import com.google.common.collect.Maps;
import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.Originator;
import de.weltraumschaf.citer.resources.BaseResource;
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
@Path("/cite/")
@Produces(MediaType.APPLICATION_JSON)
public class CiteResource extends BaseResource {

    @GET public Iterable<Cite> allCites() {
        return getCiteRepo().getAll();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response create(JSONObject jsonEntity) throws JSONException, Exception {
        if (!jsonEntity.has(Cite.TEXT)) {
            raiseMissingPropertyError(Cite.TEXT);
        }

        if (!jsonEntity.has(Originator.NAME)) {
            raiseMissingPropertyError(Originator.NAME);
        }

        final String name = jsonEntity.getString(Originator.NAME);
        Originator originator = getOriginatorRepo().findByName(name);

        if (null == originator) {
            final Map<String, Object> originatorParams  = Maps.newHashMap();
            originatorParams.put(Originator.NAME, name);
            originator = getOriginatorRepo().create(originatorParams);
        }

        final Map <String, Object> params = Maps.newHashMap();
        params.put(Cite.TEXT, jsonEntity.getString(Cite.TEXT));
        Cite newCite = getCiteRepo().create(params);
        newCite.setOriginator(originator);

        URI uri = getUriInfo().getAbsolutePathBuilder()
                              .path(newCite.getId())
                              .build();
        return Response.created(uri)
                       .entity(newCite)
                       .build();
    }

    @Path("{id}/")
    @GET public Cite get(@PathParam("id") String id) throws JSONException {
        Cite cite =  getCiteRepo().findById(id);

        if (null == cite) {
            raiseIdNotFoundError("cite", id);
        }

        return cite;
    }

    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response update(@PathParam("id") String id, JSONObject jsonEntity) throws JSONException, Exception {
        if (!jsonEntity.has(Cite.TEXT)) {
            raiseMissingPropertyError(Cite.TEXT);
        }

        final String text = jsonEntity.getString(Cite.TEXT);
        final Cite cite   =  getCiteRepo().findById(id);

        if (null == cite) {
            raiseIdNotFoundError("cite", id);
        }

        cite.setText(text);
        cite.setDateUpdated(new DateTime());

        if (jsonEntity.has(Originator.NAME)) {
            String name = jsonEntity.getString(Originator.NAME);

            Originator originator = getOriginatorRepo().findByName(name);

            if (null == originator) {
                Map params  = new HashMap<String, Object>();
                params.put(Originator.NAME, name);
                originator = getOriginatorRepo().create(params);
                cite.setOriginator(originator);
            } else if (!cite.getOriginator().equals(originator)) {
                cite.setOriginator(originator);
            }
        }

        URI uri = getUriInfo().getAbsolutePathBuilder()
                              .path(cite.getId())
                              .build();
        return Response.created(uri)
                       .entity(cite)
                       .build();
    }

    @Path("{id}/")
    @DELETE public Response delete(@PathParam("id") String id) {
        Cite cite =  getCiteRepo().findById(id);

        if (null == cite) {
            raiseIdNotFoundError("cite", id);
        }

        getCiteRepo().delete(cite);
        return Response.noContent()
                       .build();
    }

    @Path("{id}/originator/")
    @GET public Originator originator(@PathParam("id") String id) {
        Cite cite =  getCiteRepo().findById(id);

        if (null == cite) {
            raiseIdNotFoundError("cite", id);
        }


        return cite.getOriginator();
    }

    @Path("bulkupload")
    @PUT public Response upload(JSONArray jsonEntity) throws JSONException, Exception {
        for (int i = 0; i < jsonEntity.length(); ++i) {
            JSONObject singleCiteData = jsonEntity.getJSONObject(i);
            create(singleCiteData);
        }

        URI uri = getUriInfo().getAbsolutePathBuilder()
                              .path("../")
                              .build()
                              .normalize();
        return Response.created(uri)
                       .build();
    }
}
