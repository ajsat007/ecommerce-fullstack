package com.example.ecommerce.service;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  public UserDetailsServiceImpl(UserRepository repo){ this.userRepository = repo; }
  @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: "+username));
    var authorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
  }
}
