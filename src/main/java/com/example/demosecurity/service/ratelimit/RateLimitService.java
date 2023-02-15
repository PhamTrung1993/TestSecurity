package com.example.demosecurity.service.ratelimit;

import com.example.demosecurity.entity.PricingPlan;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class RateLimitService {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String role) {
        return cache.computeIfAbsent(role, this::newBucket);
    }

    private Bucket newBucket(String role) {
       String pricingPlan = PricingPlan.resolvePlanFromRole(role);
        return Bucket.builder()
                .addLimit(PricingPlan.getLimit(pricingPlan))
                .build();
    }
}
