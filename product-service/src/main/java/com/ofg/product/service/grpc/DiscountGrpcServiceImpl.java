package com.ofg.product.service.grpc;

import com.ofg.grpc.DiscountRequest;
import com.ofg.grpc.DiscountResponse;
import com.ofg.grpc.DiscountServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscountGrpcServiceImpl implements DiscountGrpcService {
    private DiscountServiceGrpc.DiscountServiceBlockingStub discountServiceBlockingStub;
    private final ManagedChannel managedChannel;

    @Autowired
    public DiscountGrpcServiceImpl(@Value("${discount.grpc.host}") String grpcHost,
                                   @Value("${discount.grpc.port}") int grpcPort) {
        managedChannel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }

    @Override
    public DiscountResponse getDiscount(DiscountRequest discountRequest) {
        discountServiceBlockingStub = DiscountServiceGrpc.newBlockingStub(managedChannel);
        return discountServiceBlockingStub.getDiscount(discountRequest);
    }
}
