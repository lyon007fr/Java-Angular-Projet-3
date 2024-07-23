package com.openclassrooms.ApiRentals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig {

	
    @Bean
    public WebMvcConfigurer corsConfigurer()  {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // HTTP method autorize
                .allowedHeaders("*") // Autorize all headers
                .allowCredentials(true); // Autorize cookie and authentification information
                
            }
            
            
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // Configure the resource handler to serve files from the "uploads" directory
            	String uploadsPath = "file:///C:/Users/mbenziane/Repo Git/python/Java/ApiRentals/src/main/resources/static/uploads/";
                //logger.info("Serving static files from: {}", uploadsPath);
                registry.addResourceHandler("/api/uploads/**")
                        .addResourceLocations(uploadsPath);
            }
        };
    }
}