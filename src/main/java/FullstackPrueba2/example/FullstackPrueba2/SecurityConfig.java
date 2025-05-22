package FullstackPrueba2.example.FullstackPrueba2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuarios/**").permitAll()
                        .requestMatchers("/cursos/**").permitAll()
                        .requestMatchers("/inscripciones/**").permitAll()
                        .requestMatchers("/evaluaciones/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(); //

        return http.build();
    }
}