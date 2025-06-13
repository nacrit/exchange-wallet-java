package com.nacrt.exchange.wallet.demo.service;

import com.nacrt.exchange.wallet.demo.dao.ProductRepository;
import com.nacrt.exchange.wallet.demo.model.Product;
import com.nacrt.exchange.wallet.demo.model.ProductRequest;
import com.nacrt.exchange.wallet.demo.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Product createProduct(ProductRequest request) {
        Product product = new Product();
//        product.setName(request.name());
//        product.setDescription(request.description());
//        product.setPrice(request.price());
//        product.setQuantity(request.quantity());
        return productRepository.save(product);
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Long id, ProductRequest request) {
        Product product = getProductById(id);
//        product.setName(request.name());
//        product.setDescription(request.description());
//        product.setPrice(request.price());
//        product.setQuantity(request.quantity());
        return productRepository.save(product);
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }
}