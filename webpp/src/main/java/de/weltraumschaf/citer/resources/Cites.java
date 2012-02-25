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
@Path("/cite/")
public class Cites {

    private final Data model = Factory.getModel();

    @Context UriInfo uriInfo;

    private Cite findById(String id) {
        Cite cite = model.getCiteById(id);

        if (null == cite) {
            throw new NotFoundException(String.format("Can't find cite with id %s.", id));
        }

        return cite;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET public JSONArray allCites() {
        JSONArray cites = new JSONArray();

        for (Cite cite : model.getCites()) {
            cites.put(uriInfo.getAbsolutePath() + cite.getId());
        }

        return cites;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response create(JSONObject jsonEntity) throws JSONException {
        return Response.created(uriInfo.getAbsolutePath()).build();
    }

    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public Cite get(@PathParam("id") String id) throws JSONException {
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

    @Path("{id}/originator")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public Originator originator(@PathParam("id") String id) {
        return findById(id).getOriginator();
    }

    @Path("random/")
    public RandomCite randomCite() {
        return new RandomCite();
    }
}