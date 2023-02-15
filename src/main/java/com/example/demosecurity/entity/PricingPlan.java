package com.example.demosecurity.entity;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import java.security.Principal;
import java.time.Duration;
import java.util.Scanner;

public class PricingPlan {

    public static String resolvePlanFromRole(String role) {
        if (role.startsWith("ROLE_USER")) {
            return "FREE";
        } else if (role.startsWith("ROLE_ADMIN")) {
            return "BASIC";
        }
        return "FREE";
    }

    public static Bandwidth getLimit(String name){
        if ("BASIC".equals(name)) {
            return Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        } else {
            return Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập phân quyền: ");
        String role = scanner.nextLine();
        System.out.println(role);
        String pricingPlan = resolvePlanFromRole(role);
        System.out.println(pricingPlan);
        Bandwidth limit = getLimit(pricingPlan);
        System.out.println(Bucket.builder()
                .addLimit(limit)
                .build());
    }
}
