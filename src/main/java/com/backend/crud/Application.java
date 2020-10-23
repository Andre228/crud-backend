package com.backend.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfig() {
//        return new WebMvcConfigurerAdapter() {
//            public void addCorsMapping(CorsRegistry corsRegistry) {
//                corsRegistry.addMapping("/**");
//            }
//        };
//    }

}