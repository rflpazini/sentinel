package com.rflpazini.xgroup.controller;

import com.rflpazini.xgroup.adapter.StatsAdapter;
import com.rflpazini.xgroup.service.StatsService;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/stats")
public class StatsController {
  @Autowired StatsService statsService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStats() {
    StatsAdapter stats = statsService.countMutants();
    if (stats == null) {
      throw new BadRequestException();
    }

    return Response.ok().entity(stats).build();
  }
}
