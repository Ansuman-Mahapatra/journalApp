package com.first_project.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // authorize routes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello").permitAll()  // open endpoint
                        .anyRequest().authenticated()           // all others need login
                )

                // enable both login methods
                .formLogin(Customizer.withDefaults())    // ✅ modern non-deprecated version
                .httpBasic(Customizer.withDefaults());   // ✅ modern replacement

        return http.build();
    }
}
