package com.rflpazini.xgroup.config;

import com.rflpazini.xgroup.controller.MutantController;
import com.rflpazini.xgroup.controller.SentinelController;
import com.rflpazini.xgroup.controller.StatsController;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api/v0.1")
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(SentinelController.class);
    register(MutantController.class);
    register(StatsController.class);
  }
}
