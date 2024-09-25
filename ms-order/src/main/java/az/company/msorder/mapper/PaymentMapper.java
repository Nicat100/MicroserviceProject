package az.company.msorder.mapper;

import az.company.msorder.dao.entity.OrderEntity;
import az.company.msorder.model.client.request.CreatePaymentRequest;
import az.company.msorder.model.request.CreateOrderRequest;

import java.math.BigDecimal;

import static java.util.UUID.*;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public CreatePaymentRequest mapToCreatePaymentRequest(OrderEntity orderEntity,
                                                          CreateOrderRequest createOrderRequest,
                                                          BigDecimal totalAmount) {
        return CreatePaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentType(createOrderRequest.getPaymentType())
                .amount(totalAmount)
                .referenceNumber(randomUUID().toString())
                .build();
    }
}
