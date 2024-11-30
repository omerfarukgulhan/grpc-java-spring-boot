package com.ofg.product.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiscountResponse {
    private float newPrice;
    private float oldPrice;
    private String code;
}