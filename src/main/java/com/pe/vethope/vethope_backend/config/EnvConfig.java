package com.pe.vethope.vethope_backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @Bean
    public Dotenv dotenv() {
        // Carga el archivo .env desde la raíz del proyecto
        return Dotenv.configure()
                .ignoreIfMissing() // evita error si no hay .env (por ejemplo, en despliegue)
                .load();
    }
}
