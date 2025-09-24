package com.example.ecommerce.util;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Set;
@Component
public class DataLoader implements CommandLineRunner {
  private final RoleRepository roleRepository; private final UserRepository userRepository; private final CategoryRepository categoryRepository; private final ProductRepository productRepository; private final PasswordEncoder encoder;
  public DataLoader(RoleRepository roleRepository, UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository, PasswordEncoder encoder){ this.roleRepository=roleRepository; this.userRepository=userRepository; this.categoryRepository=categoryRepository; this.productRepository=productRepository; this.encoder=encoder; }
  @Override public void run(String... args) throws Exception{ if(roleRepository.count()==0){ Role admin = roleRepository.save(new Role(null, "ROLE_ADMIN")); Role userR = roleRepository.save(new Role(null, "ROLE_USER")); if(!userRepository.existsByEmail("admin@example.com")){ User adminUser = new User(); adminUser.setEmail("admin@example.com"); adminUser.setPassword(encoder.encode("Admin@123")); adminUser.setName("Admin"); adminUser.setRoles(Set.of(admin, userR)); userRepository.save(adminUser); }
    if(!userRepository.existsByEmail("user@example.com")){ User normal = new User(); normal.setEmail("user@example.com"); normal.setPassword(encoder.encode("User@123")); normal.setName("Customer"); normal.setRoles(Set.of(userR)); userRepository.save(normal); }
    var electronics = categoryRepository.save(new Category(null, "Electronics")); var apparel = categoryRepository.save(new Category(null, "Apparel")); productRepository.save(new Product(null, "Sample Phone", "A great phone", BigDecimal.valueOf(699.00), 50, "https://placehold.co/600x400", electronics)); productRepository.save(new Product(null, "T-Shirt", "Comfort T-shirt", BigDecimal.valueOf(19.99), 200, "https://placehold.co/600x400", apparel)); }
  }
}
