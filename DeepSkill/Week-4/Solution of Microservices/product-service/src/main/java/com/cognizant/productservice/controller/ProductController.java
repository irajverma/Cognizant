package com.cognizant.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RefreshScope
public class ProductController {

    // Resolves property from Central Config Server
    @Value("${message:Hello from local fallback config!}")
    private String message;

    @Value("${stock.level.limit:100}")
    private int stockLimit;

    // Product Service Endpoint
    @GetMapping("/products")
    public List<Map<String, Object>> getProducts() {
        List<Map<String, Object>> products = new ArrayList<>();
        Map<String, Object> p1 = new HashMap<>();
        p1.put("id", 101L);
        p1.put("name", "Smartphone");
        p1.put("price", 49999.0);
        products.add(p1);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("id", 102L);
        p2.put("name", "Laptop");
        p2.put("price", 79999.0);
        products.add(p2);

        return products;
    }

    // Inventory Service Endpoint (demonstrates stock tracking using central config limit)
    @GetMapping("/inventory/{productId}")
    public Map<String, Object> getInventory(@PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();
        response.put("productId", productId);
        response.put("stockLevel", 35);
        response.put("limit", stockLimit);
        response.put("status", 35 < stockLimit ? "RESTOCK_REQUIRED" : "OK");
        response.put("configMessage", message);
        return response;
    }
}
