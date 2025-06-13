package com.nacrt.exchange.wallet.demo.controller;

import com.nacrt.exchange.wallet.demo.model.ApiResponse;
import com.nacrt.exchange.wallet.demo.model.Product;
import com.nacrt.exchange.wallet.demo.model.ProductRequest;
import com.nacrt.exchange.wallet.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "Endpoints for managing products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name")
    public ResponseEntity<ApiResponse<List<Product>>> searchProducts(@RequestParam String name) {
        List<Product> products = productService.searchByName(name);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter products by price range")
    public ResponseEntity<ApiResponse<Page<Product>>> filterProductsByPrice(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice,
            Pageable pageable) {
        Page<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice, pageable);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create a new product", description = "Requires ADMIN role")
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody ProductRequest request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(ApiResponse.success(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update a product", description = "Requires ADMIN role")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        Product product = productService.updateProduct(id, request);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete a product", description = "Requires ADMIN role")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}