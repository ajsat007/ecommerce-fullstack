package com.example.ecommerce.controller;
import com.example.ecommerce.model.Product; import com.example.ecommerce.repository.ProductRepository; import org.springframework.data.domain.*; import org.springframework.http.ResponseEntity; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductRepository productRepository;
  public ProductController(ProductRepository productRepository){ this.productRepository = productRepository; }
  @GetMapping public Page<Product> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size){ return productRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending())); }
  @GetMapping("/{id}") public ResponseEntity<Product> get(@PathVariable Long id){ return productRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
  @PreAuthorize("hasAuthority('ROLE_ADMIN')") @PostMapping public Product create(@RequestBody Product product){ return productRepository.save(product); }
  @PreAuthorize("hasAuthority('ROLE_ADMIN')") @PutMapping("/{id}") public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product payload){ return productRepository.findById(id).map(p->{ p.setName(payload.getName()); p.setDescription(payload.getDescription()); p.setPrice(payload.getPrice()); p.setStock(payload.getStock()); p.setImageUrl(payload.getImageUrl()); p.setCategory(payload.getCategory()); return ResponseEntity.ok(productRepository.save(p)); }).orElseGet(() -> ResponseEntity.notFound().build()); }
  @PreAuthorize("hasAuthority('ROLE_ADMIN')") @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ productRepository.deleteById(id); return ResponseEntity.noContent().build(); }
}
