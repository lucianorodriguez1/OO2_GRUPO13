package com.oo2.grupo13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.oo2.grupo13"})
public class Grupo13Application {
    public static void main(String[] args) {
        SpringApplication.run(Grupo13Application.class, args);
    }
}

