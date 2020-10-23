package com.backend.crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * Created by Андрей on 19.10.2020.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {



    private String[] allowedOrigins = { "http://localhost:4200", "http://localhost:8080", "http://localhost:8081" };

    // add Access-Control-Expose-Headers
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(Arrays.asList(allowedOrigins));
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allHttpMethods());
    }

    private String[] allHttpMethods(){
        return stream(HttpMethod.values())
                .map(HttpMethod::toString)
                .collect(Collectors.toList())
                .toArray(new String[HttpMethod.values().length]);
    }
}
