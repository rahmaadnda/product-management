package com.example.productmanagement.controller;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/generate")
    public Map<String, Object> generateData() {
        int count = productService.generateProducts();
        return Map.of(
                "message", "Generate data berhasil.",
                "productsCreated", count
        );
    }

    @GetMapping("/search")
    public List<Product> searchData(@RequestParam("q") String query) {
        return productService.searchProducts(query);
    }

    @GetMapping("/all")
    public List<Product> getAllData() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/clear")
    public Map<String, String> clearData() {
        productService.clearAllProducts();
        return Map.of("message", "Semua data telah dihapus.");
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }
}
