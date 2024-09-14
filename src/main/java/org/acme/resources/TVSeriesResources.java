package org.acme.resources;

import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.proxy.TvSeriesProxy;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Logger;

@Path("/tvseries")
public class TVSeriesResources {

    @RestClient
    TvSeriesProxy tvSeriesProxy;

    public static final Logger logger = Logger.getLogger("TVSeriesResources");

    @GET
    @Path("/{id}")
    @Fallback(fallbackMethod = "getTvSeriesByIdFallback")
    @Retry(maxRetries = 1)
    @Timeout(2000)
    @CircuitBreaker(requestVolumeThreshold = 3, delay = 1000, failureRatio = 0.1)
    public Response getTvSeriesById(@PathParam("id") int id) {
        logger.info("Inside getTvSeriesById API: "+id);
        return Response.ok(tvSeriesProxy.getTvSeriesById(id)).build();
    }

    public Response getTvSeriesByIdFallback(int id) {
        return Response.ok("Site is under maintenance").build();
    }

    @GET
    @Path("/person/{personname}")
    public JsonArray getTvSeriesById(@PathParam("personname") String personName){
        return tvSeriesProxy.getTvSeriesByPersonName(personName);
    }
}
