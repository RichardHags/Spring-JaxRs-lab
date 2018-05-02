package se.totoro.todoLab.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig(){
        packages("se.totoro.todoLab.resource");
    }
}
