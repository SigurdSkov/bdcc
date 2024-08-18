package dk.BdCC.bankaccountsystem.controllers;

import dk.BdCC.bankaccountsystem.models.CounterEntityRequestDTO;
import dk.BdCC.bankaccountsystem.models.CounterEntityResponseDTO;
import dk.BdCC.bankaccountsystem.models.entities.CounterEntity;
import dk.BdCC.bankaccountsystem.service.CounterService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

@Path("api/counter")
public class CounterController {
    @Inject
    CounterService counterService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<CounterEntity> getAllCounters() {
        return counterService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public long getCount(@PathParam("id") String id) {
        return counterService.findCount(id).count;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CounterEntityResponseDTO createCounter(CounterEntityRequestDTO ce) {
        return counterService.createCounter(ce);
    }
}

/*    @PUT
    @Path("/increment")
    @Produces(MediaType.APPLICATION_JSON)
    public String increment(long id) {
        return counterService.findById(id).count+1;
    }*/



/*    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<CounterEntity> getAllCounters() {
        return counterService.findAll();
    }*/