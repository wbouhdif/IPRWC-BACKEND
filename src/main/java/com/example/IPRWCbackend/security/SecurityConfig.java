package com.example.IPRWCbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTFilter filter;


    @Autowired
    public SecurityConfig(JWTFilter filter) {
        this.filter = filter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/account").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/product/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/product/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/product").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/product{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/product").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/promo-code").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/promo-code").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/promo-code").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/promo-code/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/role").hasAuthority("ROLE_ADMIN")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized")
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        final String GET = "GET";
        final String POST = "POST";
        final String PUT = "PUT";
        final String DELETE = "DELETE";
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*")
                .allowedMethods(GET, POST, PUT, DELETE)
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}