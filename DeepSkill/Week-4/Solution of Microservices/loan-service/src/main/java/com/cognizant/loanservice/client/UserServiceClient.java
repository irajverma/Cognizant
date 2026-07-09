package com.cognizant.loanservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "account-service")
public interface UserServiceClient {

    @GetMapping("/users/{id}")
    Map<String, Object> getUserById(@PathVariable("id") Long id);
}
