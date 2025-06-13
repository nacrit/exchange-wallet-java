package com.nacrt.exchange.wallet.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nacrt.exchange.wallet.demo.model.Product;
import com.nacrt.exchange.wallet.demo.model.ProductRequest;
import com.nacrt.exchange.wallet.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProducts() throws Exception {
        Product product1 = new Product(1L, "Laptop", "High performance laptop", new BigDecimal("999.99"), 10, 0L);
        Product product2 = new Product(2L, "Phone", "Smart phone", new BigDecimal("499.99"), 20, 0L);
        List<Product> products = Arrays.asList(product1, product2);

        Mockito.when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].name", is("Laptop")))
                .andExpect(jsonPath("$.data[1].name", is("Phone")));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createProduct() throws Exception {
        ProductRequest request = new ProductRequest("Tablet", "Portable tablet", new BigDecimal("299.99"), 15);
        Product createdProduct = new Product(1L, "Tablet", "Portable tablet", new BigDecimal("299.99"), 15, 0L);

        Mockito.when(productService.createProduct(any(ProductRequest.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name", is("Tablet")))
                .andExpect(jsonPath("$.data.price", is(299.99)));
    }

    @Test
    void getProductById() throws Exception {
        Product product = new Product(1L, "Laptop", "High performance laptop", new BigDecimal("999.99"), 10, 0L);

        Mockito.when(productService.getProductById(anyLong())).thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Laptop")));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)));
    }
}