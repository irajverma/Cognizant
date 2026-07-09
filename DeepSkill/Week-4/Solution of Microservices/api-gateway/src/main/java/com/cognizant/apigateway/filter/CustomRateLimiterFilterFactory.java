package com.cognizant.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Custom Rate Limiter Gateway Filter that works out of the box without requiring Redis.
 * Limits requests per client IP address.
 */
@Component
public class CustomRateLimiterFilterFactory extends AbstractGatewayFilterFactory<CustomRateLimiterFilterFactory.Config> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRateLimiterFilterFactory.class);
    private final ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS_PER_MINUTE = 10;

    public CustomRateLimiterFilterFactory() {
        super(Config.class);
        // Reset counts periodically
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000);
                    requestCounts.clear();
                    LOGGER.debug("Rate Limiter counters reset.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            requestCounts.putIfAbsent(ip, new AtomicInteger(0));
            int count = requestCounts.get(ip).incrementAndGet();

            if (count > MAX_REQUESTS_PER_MINUTE) {
                LOGGER.warn("Rate limit exceeded for client IP: {}. Requests this minute: {}", ip, count);
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
        // configuration parameters if needed
    }
}
