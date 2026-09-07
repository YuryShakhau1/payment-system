package by.shakhau.ps.core.config;

import by.shakhau.ps.core.controller.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreExceptionsConfiguration {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(
            @Value("${spring.profiles.active:local}") String activeProfile) {
        return new GlobalExceptionHandler(activeProfile);
    }
}
