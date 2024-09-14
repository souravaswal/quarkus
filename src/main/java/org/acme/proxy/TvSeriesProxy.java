package org.acme.proxy;

import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.acme.model.TVSeries;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TvSeriesProxy {

    @GET
    @Path("/shows/{id}")
    TVSeries getTvSeriesById(@PathParam("id") int id);

    @GET
    @Path("search/people")
    JsonArray getTvSeriesByPersonName(@QueryParam("q") String personName);
}
