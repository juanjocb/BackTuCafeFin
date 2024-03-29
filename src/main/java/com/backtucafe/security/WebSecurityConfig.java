package com.backtucafe.security;

import com.backtucafe.security.jwtauthentication.JWTAuthenticationFilter;
import com.backtucafe.security.jwtauthentication.JWTAuthenticationFilterBusiness;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        return http
//               .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/lugares", "/acerca", "/opiniones", "/reserva",
                                "/tuCafe/v1/business/register", "/tuCafe/v1/client/register", "/tuCafe/v1/client/login",
                                "/tuCafe/v1/business/login", "/tuCafe/v1/client/{id_client}", "/tuCafe/v1/client/listBusiness",
                                "/tuCafe/v1/business/{id_business}", "/tuCafe/v1/reservation/creation_reservation",
                                "/tuCafe/v1/admin/login", "/tuCafe/v1/admin/getAdmin", "/tuCafe/v1/admin/changeStatus/{idBusiness}",
                                "/tuCafe/v1/business/getBusinessNotActive", "/tuCafe/v1/business/getBusinessActive", "/tuCafe/v1/client/listBusiness", "/tuCafe/v1/view/newView").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sessionM -> sessionM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:5173");
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowCredentials(false);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }



    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
