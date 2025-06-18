package com.Ahmed.SoltanSalman.comman_helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Random;

@Configuration
public class ConfigClass {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*") // Consider restricting to specific origins in production
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Explicitly allow PUT
//                        .allowedHeaders("*") // Allow all headers
//                        .allowCredentials(false); // Set to true if cookies or auth headers are needed
//            }
//        };
//    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory factory) {
        return new MongoTemplate(factory);
    }

    @Bean
    public Random random() {
        return new Random();
    }

}

