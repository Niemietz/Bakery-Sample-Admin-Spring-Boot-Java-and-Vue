package com.niemietz.bakerysample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        // 1. Se o arquivo existe fisicamente → devolve ele (js, css, imagens, favicon)
                        try {
                            Resource resource = location.createRelative(resourcePath);
                            if (resource.exists() && resource.isReadable()) {
                                return resource;
                            }
                        } catch (Exception e) {
                            // ignora
                        }

                        // 2. SE for caminho da API → NÃO devolve index.html (deixa dar 404 JSON)
                        if (resourcePath.startsWith("api/") || resourcePath.startsWith("ws/")) {
                            return null;        // ← crucial: deixa o Spring procurar @RestController
                        }

                        // 3. Qualquer outro caminho → fallback para Vue Router
                        return new ClassPathResource("/static/index.html");
                    }
                });
    }
}