package net.company.apigateway.config;

import net.company.common.JwtUtility.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
