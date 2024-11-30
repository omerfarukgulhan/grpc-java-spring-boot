package com.ofg.product.service;

import com.ofg.grpc.DiscountRequest;
import com.ofg.grpc.DiscountResponse;
import com.ofg.product.model.entity.Product;
import com.ofg.product.service.grpc.DiscountGrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    private final DiscountGrpcService discountGrpcService;
    private final ProductService productService;

    @Autowired
    public DiscountService(DiscountGrpcService discountGrpcService, ProductService productService) {
        this.discountGrpcService = discountGrpcService;
        this.productService = productService;
    }

    public DiscountResponse getDiscount(int productId, String code) {
        Product product = productService.getById(productId);
        DiscountRequest discountRequest = DiscountRequest.newBuilder()
                .setCode(code)
                .setPrice(product.getPrice().floatValue())
                .setExternalCategoryId(product.getCategory().getId())
                .build();
        return discountGrpcService.getDiscount(discountRequest);
    }
}