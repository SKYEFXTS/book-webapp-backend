package com.example.Book.Webapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    
    /**
     * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
     * This allows the frontend (running on a different domain/port) to communicate with the backend.
     *
     * @return A WebMvcConfigurer object to apply CORS configurations.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Applying CORS settings for all paths in the backend
                registry.addMapping("/**")
                        // Allows requests from the frontend URL (React app)
                        .allowedOrigins("http://localhost:3000")
                        // Specifies the allowed HTTP methods
                        .allowedMethods("GET");
            }
        };
    }
    
}
