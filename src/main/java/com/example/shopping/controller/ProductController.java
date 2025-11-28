package com.example.shopping.controller;

import com.example.shopping.model.Product;
import com.example.shopping.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService service;
  public ProductController(ProductService service) { this.service = service; }

  @GetMapping
  public List<Product> list() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> get(@PathVariable Long id) {
    return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product) {
    Product saved = service.save(product);
    return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
    return service.findById(id).map(existing -> {
      existing.setName(product.getName());
      existing.setDescription(product.getDescription());
      existing.setPrice(product.getPrice());
      Product updated = service.save(existing);
      return ResponseEntity.ok(updated);
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (service.findById(id).isEmpty()) return ResponseEntity.notFound().build();
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
