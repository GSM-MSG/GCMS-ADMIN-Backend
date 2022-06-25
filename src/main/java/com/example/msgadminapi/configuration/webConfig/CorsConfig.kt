package com.example.msgadminapi.configuration.webConfig

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedHeaders("*")
            .allowCredentials(true)
            .allowedHeaders("*")
            .allowedMethods("GET", "POST", "PATCH", "DELETE", "PUT")
            .maxAge(3000)
    }
}