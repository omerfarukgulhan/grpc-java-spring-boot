package com.ofg.product.controller;

import com.ofg.product.model.response.DiscountResponse;
import com.ofg.product.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<DiscountResponse> getDiscount(int productId, String code) {
        com.ofg.grpc.DiscountResponse discountResponse = discountService.getDiscount(productId, code);
        return ResponseEntity.ok(
                DiscountResponse.builder()
                        .newPrice(discountResponse.getNewPrice())
                        .oldPrice(discountResponse.getOldPrice())
                        .code(discountResponse.getCode())
                        .build()
        );
    }
}