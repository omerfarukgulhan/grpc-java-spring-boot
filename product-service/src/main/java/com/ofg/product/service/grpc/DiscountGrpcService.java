package com.ofg.product.service.grpc;

import com.ofg.grpc.DiscountRequest;
import com.ofg.grpc.DiscountResponse;

public interface DiscountGrpcService {
    DiscountResponse getDiscount(DiscountRequest discountRequest);
}
