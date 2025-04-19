package com.cap.admin.catalogo.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import com.cap.admin.catalogo.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,
                "development");
        // System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,
        // "test");
        SpringApplication.run(WebServerConfig.class, args);
    }
}