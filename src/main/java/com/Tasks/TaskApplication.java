package com.Tasks;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.Collections;


@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(com.Tasks.TaskApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        app.run(args);
    }

}

