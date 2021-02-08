package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CompetitiveConnect4 {
    public static void main(String[] args){
        SpringApplication.run(CompetitiveConnect4.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "OPTIONS", "PUT", "POST", "DELETE", "PATCH")
                        .allowedOrigins("http://localhost:4200").allowedHeaders("*").allowCredentials(true);
            }
        };
    }

}
