package com.zorvyn.finance.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) //
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/zorvyn/records/**")
                        .hasAnyRole("ADMIN", "ANALYST")
                        .requestMatchers("/api/v1/zorvyn/dashboard/**")
                        .hasAnyRole("ADMIN", "ANALYST", "VIEWER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // ✅ fixed

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password("{noop}pass").roles("ADMIN").build(),
                User.withUsername("analyst").password("{noop}pass").roles("ANALYST").build(),
                User.withUsername("viewer").password("{noop}pass").roles("VIEWER").build()
        );
    }
}
