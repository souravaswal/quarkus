package org.acme.resources;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Mobile;

import java.util.ArrayList;
import java.util.List;

@Path("/mobile")
public class MobileResources {

    List<Mobile> listOfMobiles = new ArrayList<>();

    /**
     * This function is responsible for getting list of mobiles
     * @return list of mobiles
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList() {
        return Response.ok(listOfMobiles).build();
    }

    /**
     * This function is responsible for adding new mobiles to the list
     * @param mobile Mobile to be added
     * @return Updated list of mobiles
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMobile(Mobile mobile) {
        listOfMobiles.add(mobile);
        return Response.ok(mobile).status(201).build();
    }

    /**
     * This function is responsible for updating mobiles to the list
     * @param id I'd of the mobile to be updated
     * @param updatedMobile Body of the updated mobile
     * @return Updated list of mobiles
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("id") int id, Mobile updatedMobile) {
        listOfMobiles = listOfMobiles.stream().map(mobile -> {
            if (mobile.getId() == id) {
                return updatedMobile;
            } return mobile;
        }).toList();
        return Response.ok(listOfMobiles).status(201).build();
    }

    /**
     * This controller is to delete the mobile phones
     * @param mobileId I'd of the mobile to be deleted from the list
     * @return Updated list of mobiles
     */
    @DELETE
    @Path("/{id}")
    public Response deleteMobile(@PathParam("id") int mobileId) {
        listOfMobiles = listOfMobiles.stream().filter(mobile -> mobile.getId() != mobileId).toList();
        return Response.ok(listOfMobiles).status(201).build();
    }

}
