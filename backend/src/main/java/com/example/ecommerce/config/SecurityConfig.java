package com.example.ecommerce.config;
import com.example.ecommerce.security.JwtAuthenticationFilter;
import com.example.ecommerce.security.JwtUtils;
import com.example.ecommerce.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  private final JwtUtils jwtUtils; private final UserDetailsServiceImpl userDetailsService;
  public SecurityConfig(JwtUtils jwtUtils, UserDetailsServiceImpl uds){ this.jwtUtils = jwtUtils; this.userDetailsService = uds; }
  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtUtils, userDetailsService);
    http.csrf().disable().authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll().anyRequest().authenticated()).sessionManagement(session -> session.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
  @Bean public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
  @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { return config.getAuthenticationManager(); }
}
