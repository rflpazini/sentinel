package com.rflpazini.xgroup.controller;

import com.rflpazini.xgroup.model.Mutant;
import com.rflpazini.xgroup.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Component
@Path("/mutant")
public class MutantController {
    Logger logger = Logger.getLogger(MutantController.class.getName());

    @Autowired
    MutantService mutantService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMutants() {
        List<Mutant> mutants = mutantService.getMutants();
        return Response.ok().entity(mutants).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMutantRegistry(@Valid Mutant mutant) {
        boolean xGense = mutantService.isMutant(mutant);
        if (!xGense) {
            throw new ForbiddenException("This DNA does not belongs to a Mutant");
        }

        return Response.ok().build();
    }
}
