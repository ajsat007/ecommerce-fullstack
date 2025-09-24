package com.example.ecommerce.security;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtUtils { @Value("${jwt.secret}") private String jwtSecret; @Value("${jwt.expiration-ms}") private Long jwtExpirationMs; public String generateAccessToken(String username){ Date now=new Date(); Date expiry=new Date(now.getTime()+jwtExpirationMs); return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiry).signWith(SignatureAlgorithm.HS256, jwtSecret).compact(); } public String getEmailFromToken(String token){ return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject(); } public boolean validateToken(String token){ try{ Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token); return true;}catch(Exception ex){return false;} } }
