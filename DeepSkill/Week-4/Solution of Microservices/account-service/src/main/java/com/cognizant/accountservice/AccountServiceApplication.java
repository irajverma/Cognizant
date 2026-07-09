package com.cognizant.accountservice;

import com.cognizant.accountservice.model.User;
import com.cognizant.accountservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("Iraj Verma", "iraj@cognizant.com"));
            userRepository.save(new User("John Doe", "john.doe@example.com"));
            userRepository.save(new User("Jane Smith", "jane.smith@example.com"));
        };
    }
}
