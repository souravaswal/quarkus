package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Laptop;
import org.acme.repository.LaptopRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/laptop")
public class LaptopResource {

    @Inject
    LaptopRepository laptopRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptop() {
        List<Laptop> laptopList = laptopRepository.listAll();
        return Response.ok(laptopList).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveLaptop(Laptop laptop) {
        System.out.println("Laptop Name: "+laptop.getName());
        laptopRepository.persist(laptop);
        if (laptopRepository.isPersistent(laptop)) {
            return Response.created(URI.create("/laptop/"+laptop.getId())).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParticularLaptop(@PathParam("id") Long id) {
        Laptop laptop = laptopRepository.findById(id);
        return Response.ok(laptop).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLaptop(@PathParam("id") Long id, Laptop laptop) {
        Optional<Laptop> fetchedLaptop  = laptopRepository.findByIdOptional(id);
        if (fetchedLaptop.isPresent()) {
            Laptop dbLaptop = fetchedLaptop.get();
            dbLaptop.setName(laptop.getName());
            dbLaptop.setBrand(laptop.getBrand());
            dbLaptop.setRam(laptop.getRam());
            dbLaptop.setExternalStorage(laptop.getExternalStorage());
            laptopRepository.persist(dbLaptop);
            if (laptopRepository.isPersistent(dbLaptop)) {
                return Response.created(URI.create("/laptop/"+id)).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLaptop(@PathParam("id") Long id) {
        boolean isDeleted = laptopRepository.deleteById(id);
        if (isDeleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
