package com.example.demosecurity.controller;

import com.example.demosecurity.entity.Role;
import com.example.demosecurity.entity.User;
import com.example.demosecurity.service.User.UserService;
import com.example.demosecurity.service.ratelimit.RateLimitService;
import com.example.demosecurity.service.role.RoleService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private RateLimitService rateLimitService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        String role = roleService.findRoleNameByUserId(id);
        Bucket bucket = rateLimitService.resolveBucket(role);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            new ResponseEntity<>(userOptional, HttpStatus.OK);
        }
        long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .header("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill))
                .build();
    }

}
