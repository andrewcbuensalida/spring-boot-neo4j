package com.anhonestobserver.springbootneo4j.config;

import com.anhonestobserver.springbootneo4j.services.NeoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final NeoUserDetailsService neoUserDetailsService;

    public SecurityConfig(NeoUserDetailsService neoUserDetailsService) {
        this.neoUserDetailsService = neoUserDetailsService;
    }
    // Bean annotation is so spring boot will instantiate this class. By default, the spring security uses a randomly generated user. We don't want that. We want a user from our database, that's why we have this code. Also, by default, it requires authentication for every request. We don't want that either.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable) // can disable because it's an API?
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("api/v1/auth/me") // protect requests to this endpoint and pass it to neoUserDetailsService below. It uses the User model's getPassword.
                        .authenticated()
                        .anyRequest()
                        .permitAll()
                )
                .userDetailsService(neoUserDetailsService) // use a custom user details service
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // TODO: this should come from .env
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://127.0.0.1:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE","PUT","OPTIONS","HEAD"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization","Request-Type","Content-Type"));
        corsConfiguration.setExposedHeaders(Arrays.asList("X-Get-Header"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration); // apply to all the routes
        return urlBasedCorsConfigurationSource;
    }
    @Bean
    PasswordEncoder passwordEncoder(){ // to let spring boot know bcrypt is the password encoder. password comes from insomnia basic auth.
        return new BCryptPasswordEncoder();
    }
}
