package az.company.msorder.mapper;

import az.company.msorder.dao.entity.OrderEntity;
import az.company.msorder.model.client.response.PaymentResponse;
import az.company.msorder.model.client.response.ProductResponse;
import az.company.msorder.model.request.CreateOrderRequest;
import az.company.msorder.model.response.OrderResponse;

import java.time.LocalDateTime;

import static az.company.msorder.model.enums.OrderStatus.PENDING;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity mapToEntity(CreateOrderRequest request) {
        return OrderEntity.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .status(PENDING)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public OrderResponse maoToResponse(OrderEntity entity,
                                       ProductResponse productResponse,
                                       PaymentResponse paymentResponse) {
        return OrderResponse.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .amount(entity.getAmount())
                .status(PENDING)
                .createdAt(LocalDateTime.now())
                .product(productResponse)
                .payment(paymentResponse)
                .build();
    }
}
