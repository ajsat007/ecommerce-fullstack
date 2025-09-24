package com.example.ecommerce.controller;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager; private final UserRepository userRepository; private final RoleRepository roleRepository; private final PasswordEncoder passwordEncoder; private final JwtUtils jwtUtils;
  public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) { this.authenticationManager = authenticationManager; this.userRepository = userRepository; this.roleRepository = roleRepository; this.passwordEncoder = passwordEncoder; this.jwtUtils = jwtUtils; }
  record AuthReq(String email, String password){}
  record AuthResp(String accessToken, String tokenType){}
  @PostMapping("/register") public ResponseEntity<?> register(@Valid @RequestBody AuthReq req){ if(userRepository.existsByEmail(req.email())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Email taken"); User u=new User(); u.setEmail(req.email()); u.setPassword(passwordEncoder.encode(req.password())); u.setName(req.email().split("@")[0]); Role role = roleRepository.findByName("ROLE_USER").orElseThrow(); u.setRoles(Set.of(role)); userRepository.save(u); String token = jwtUtils.generateAccessToken(u.getEmail()); return ResponseEntity.ok(new AuthResp(token,"Bearer")); }
  @PostMapping("/login") public ResponseEntity<?> login(@RequestBody AuthReq req){ authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.email(), req.password())); String token = jwtUtils.generateAccessToken(req.email()); return ResponseEntity.ok(new AuthResp(token,"Bearer")); }
}
