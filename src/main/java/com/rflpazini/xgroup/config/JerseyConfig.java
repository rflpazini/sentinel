package com.rflpazini.xgroup.config;

import com.rflpazini.xgroup.controller.MutantController;
import com.rflpazini.xgroup.controller.StatsController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api/v0.1")
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {
        register(MutantController.class);
        register(StatsController.class);
    }
}
