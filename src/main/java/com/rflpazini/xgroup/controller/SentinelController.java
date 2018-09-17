package com.rflpazini.xgroup.controller;

import com.rflpazini.xgroup.adapter.SentinelAdapter;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")
public class SentinelController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sentinel() {
        SentinelAdapter welcomeAdapter = new SentinelAdapter();
        welcomeAdapter.setTitle("Welcome to Sentinel");
        welcomeAdapter.setMessage(" We\'ll help you to find a Mutant DNA");
        welcomeAdapter.setVersion("v0.1");

        return Response.ok().entity(welcomeAdapter).build();
    }
}
