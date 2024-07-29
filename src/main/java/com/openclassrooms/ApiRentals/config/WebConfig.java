package com.openclassrooms.ApiRentals.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig {

	Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
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
                // Configurer le gestionnaire de ressources pour qu'il serve les fichiers du répertoire « uploads ».
            	//chemin qui point vers le dossier static du projet Spring.
            	String uploadsPath ="classpath:/static/uploads/";
                
                registry.addResourceHandler("/api/uploads/**")
                        .addResourceLocations(uploadsPath);
            }
        };
    }
}