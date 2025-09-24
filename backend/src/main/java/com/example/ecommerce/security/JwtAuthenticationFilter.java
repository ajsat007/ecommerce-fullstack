package com.example.ecommerce.security;
import com.example.ecommerce.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtUtils jwtUtils; private final UserDetailsServiceImpl userDetailsService;
  public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsServiceImpl uds){ this.jwtUtils = jwtUtils; this.userDetailsService = uds; }
  @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException{
    String header = request.getHeader("Authorization"); if(header!=null && header.startsWith("Bearer ")){
      String token = header.substring(7); if(jwtUtils.validateToken(token)){ String email = jwtUtils.getEmailFromToken(token); UserDetails ud = userDetailsService.loadUserByUsername(email); UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities()); SecurityContextHolder.getContext().setAuthentication(auth); }
    }
    chain.doFilter(request, response);
  }
}
