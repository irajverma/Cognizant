package com.cognizant.loanservice.controller;

import com.cognizant.loanservice.client.UserServiceClient;
import com.cognizant.loanservice.model.UserOrder;
import com.cognizant.loanservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class LoanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    // 1. Loan Service Endpoint
    @GetMapping("/loans/{number}")
    public Map<String, Object> getLoanDetails(@PathVariable String number) {
        Map<String, Object> response = new HashMap<>();
        response.put("number", number);
        response.put("type", "car");
        response.put("loan", 400000);
        response.put("emi", 3258);
        response.put("tenure", 18);
        return response;
    }

    // 2. Billing Service Endpoint
    @GetMapping("/billing/{orderId}")
    public Map<String, Object> getBillingDetails(@PathVariable Long orderId) {
        UserOrder order = orderRepository.findById(orderId).orElse(null);
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", orderId);
        if (order != null) {
            response.put("amount", order.getPrice());
            response.put("tax", order.getPrice() * 0.18);
            response.put("total", order.getPrice() * 1.18);
            response.put("status", "UNPAID");
        } else {
            response.put("status", "ORDER_NOT_FOUND");
        }
        return response;
    }

    // 3. Order Service endpoints (Feign integration with User Service)
    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody UserOrder order) {
        LOGGER.info("Placing order for user: {}", order.getUserId());
        try {
            // Call account-service via Feign to verify the user exists
            Map<String, Object> user = userServiceClient.getUserById(order.getUserId());
            if (user != null) {
                UserOrder savedOrder = orderRepository.save(order);
                LOGGER.info("Order successfully placed: {}", savedOrder.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to verify user with id {}: {}", order.getUserId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User verification failed. User does not exist or Account service is down.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
    }

    @GetMapping("/orders")
    public List<UserOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    // 4. Payment Service with Resilience4j Circuit Breaker
    @GetMapping("/payments/charge")
    @CircuitBreaker(name = "paymentService", fallbackMethod = "chargeFallback")
    public ResponseEntity<String> chargePayment(@RequestParam(defaultValue = "false") boolean slow) throws InterruptedException {
        LOGGER.info("Payment service charge endpoint invoked. Slow flag: {}", slow);
        if (slow) {
            LOGGER.info("Simulating slow network call / database latency...");
            Thread.sleep(4000); // Exceeds the gateway timeout
        }
        return ResponseEntity.ok("Payment processed successfully (Response from Loan Service)");
    }

    public ResponseEntity<String> chargeFallback(boolean slow, Exception e) {
        LOGGER.warn("Payment charge failed! Fallback invoked. Exception: {}", e.getMessage());
        return ResponseEntity.ok("Payment fallback response: System is experiencing high load. Please try again later. (Resilience4j Fallback)");
    }
}
