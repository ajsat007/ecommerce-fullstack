package com.example.ecommerce.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product { @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; private String name; @Column(columnDefinition = "TEXT") private String description; private BigDecimal price; private Integer stock; private String imageUrl; @ManyToOne @JoinColumn(name = "category_id") private Category category; }
