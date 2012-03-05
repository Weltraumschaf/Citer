package de.weltraumschaf.citer.resources;

import com.sun.jersey.api.NotFoundException;
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
public class Cites extends BaseResource {

    @Context UriInfo uriInfo;

    @Produces(MediaType.APPLICATION_JSON)
    @GET public JSONArray allCites() {
        JSONArray cites = new JSONArray();

        for (Cite cite : getCiteRepo().getAll()) {
            URI citeUri = uriInfo.getAbsolutePathBuilder()
                              .path(cite.getId())
                              .build();
            cites.put(citeUri.toString());
        }

        return cites;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response create(JSONObject jsonEntity) throws JSONException {
        String text = jsonEntity.getString("text");
//        String name = jsonEntity.getString("name");
        Map params = new HashMap<String, Object>();
        params.put(Cite.TEXT, text);
        Cite newCite = getCiteRepo().create(params);

        URI citeUri = uriInfo.getAbsolutePathBuilder()
                              .path(newCite.getId())
                              .build();
        return Response.created(citeUri)
                       .build();
    }

    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public Cite get(@PathParam("id") String id) throws JSONException {
        Cite cite =  getCiteRepo().findById(id);

        if (null == cite) {
            throw new NotFoundException(String.format("Can't find cite with id %s.", id));
        }

        return cite;
    }

    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT public Response update(@PathParam("id") String id, JSONObject jsonEntity) throws JSONException {
        String text = jsonEntity.getString("text");
        Cite cite   =  getCiteRepo().findById(id);

        if (null == cite) {
            throw new NotFoundException(String.format("Can't find cite with id %s.", id));
        }

        cite.setText(text);
        URI citeUri = uriInfo.getAbsolutePathBuilder()
                              .path(cite.getId())
                              .build();
        return Response.created(citeUri)
                       .build();
    }

    @Path("{id}/")
    @DELETE public Response delete(@PathParam("id") String id) {
        Cite cite =  getCiteRepo().findById(id);

        if (null == cite) {
            throw new NotFoundException(String.format("Can't find cite with id %s.", id));
        }

        getCiteRepo().delete(cite);
        return Response.noContent()
                       .build();
    }

    @Path("{id}/originator/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET public Originator originator(@PathParam("id") String id) {
        Cite cite =  getCiteRepo().findById(id);

        if (null == cite) {
            throw new NotFoundException(String.format("Can't find cite with id %s.", id));
        }


        return cite.getOriginator();
    }

    @Path("random/")
    public RandomCite randomCite() {
        return new RandomCite();
    }
}
