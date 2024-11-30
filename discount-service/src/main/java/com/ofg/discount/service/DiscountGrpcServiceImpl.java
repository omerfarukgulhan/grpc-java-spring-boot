package com.ofg.discount.service;

import com.ofg.discount.model.entity.Category;
import com.ofg.discount.model.entity.Discount;
import com.ofg.discount.repository.CategoryRepository;
import com.ofg.discount.repository.DiscountRepository;
import com.ofg.grpc.DiscountRequest;
import com.ofg.grpc.DiscountResponse;
import com.ofg.grpc.DiscountServiceGrpc;
import com.ofg.grpc.Response;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

@GrpcService
public class DiscountGrpcServiceImpl extends DiscountServiceGrpc.DiscountServiceImplBase {
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DiscountGrpcServiceImpl(DiscountRepository discountRepository, CategoryRepository categoryRepository) {
        this.discountRepository = discountRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void getDiscount(DiscountRequest request, StreamObserver<DiscountResponse> responseObserver) {
        Category category = categoryRepository.findByExternalId(String.valueOf(request.getExternalCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category has not been found by external category ID"));

        Optional<Discount> discount = discountRepository.findByCodeAndCategoryId(request.getCode(), category.getId());
        if (discount.isPresent()) {
            BigDecimal newPrice = discount.get().getDiscountPrice().subtract(BigDecimal.valueOf(request.getPrice())).multiply(BigDecimal.valueOf(-1));
            responseObserver.onNext(
                    DiscountResponse.newBuilder()
                            .setCode(discount.get().getCode())
                            .setOldPrice(request.getPrice())
                            .setNewPrice(newPrice.floatValue())
                            .setResponse(
                                    Response.newBuilder().setStatusCode(true).setMessage("Discount has been applied successfully!").build()
                            ).build()
            );
        } else {
            responseObserver.onNext(DiscountResponse.newBuilder()
                    .setOldPrice(request.getPrice())
                    .setNewPrice(request.getPrice())
                    .setCode(request.getCode())
                    .setResponse(Response.newBuilder().setMessage("Code and category are invalid.").setStatusCode(false).build())
                    .build()
            );
        }

        responseObserver.onCompleted();
    }
}