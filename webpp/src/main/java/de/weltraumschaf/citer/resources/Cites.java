package de.weltraumschaf.citer.resources;

import de.weltraumschaf.citer.domain.Cite;
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
@Path("/cite/")
@Produces(MediaType.APPLICATION_JSON)
public class Cites extends BaseResource {

    @Context UriInfo uriInfo;

    @GET public JSONArray allCites() {
        JSONArray cites = new JSONArray();

        for (Cite cite : getCiteRepo().getAll()) {
            URI uri = uriInfo.getAbsolutePathBuilder()
                             .path(cite.getId())
                             .build();
            cites.put(uri.toString());
        }

        return cites;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response create(JSONObject jsonEntity) throws JSONException {
        if (!jsonEntity.has(Cite.TEXT)) {
            raiseMissingPropertyError(Cite.TEXT);
        }

        Map params = new HashMap<String, Object>();
        params.put(Cite.TEXT, jsonEntity.getString(Cite.TEXT));
        String now = now().toString();
        params.put(Cite.DATE_CREATED, now);
        params.put(Cite.DATE_UPDATED, now);
        Cite newCite = getCiteRepo().create(params);

        URI uri = uriInfo.getAbsolutePathBuilder()
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
    @PUT public Response update(@PathParam("id") String id, JSONObject jsonEntity) throws JSONException {
        if (!jsonEntity.has(Cite.TEXT)) {
            raiseMissingPropertyError(Cite.TEXT);
        }

        String text = jsonEntity.getString(Cite.TEXT);
        Cite cite   =  getCiteRepo().findById(id);

        if (null == cite) {
            raiseIdNotFoundError("cite", id);
        }

        cite.setText(text);
        cite.setDateUpdated(now());
        URI uri = uriInfo.getAbsolutePathBuilder()
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

    @Path("random/")
    public RandomCite randomCite() {
        return new RandomCite();
    }
}
