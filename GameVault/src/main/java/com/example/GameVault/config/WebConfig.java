package com.example.GameVault.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Las imagenes subidas se guardan en src/main/resources/static/uploads/.
 * Spring solo sirve lo que esta en el classpath (target/classes), por lo que
 * una imagen nueva no se veria hasta recompilar. Con este handler, la URL
 * /uploads/** se sirve directo desde la carpeta del disco, en tiempo real.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath.toUri().toString());
    }
}
