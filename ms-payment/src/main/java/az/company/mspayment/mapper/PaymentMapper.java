package az.company.mspayment.mapper;

import az.company.mspayment.dao.entity.PaymentEntity;
import az.company.mspayment.model.request.CreatePaymentRequest;
import az.company.mspayment.model.response.PaymentResponse;

import static az.company.mspayment.model.enums.PaymentStatus.SUCCESS;
import static java.time.LocalDateTime.now;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public PaymentEntity mapToPaymentEntity(CreatePaymentRequest request) {
        return PaymentEntity.builder()
                .orderId(request.getOrderId())
                .paymentType(request.getPaymentType())
                .referenceNumber(request.getReferenceNumber())
                .amount(request.getAmount())
                .status(SUCCESS)
                .createdAt(now())
                .build();
    }

    public PaymentResponse mapToPaymentResponse(PaymentEntity paymentEntity) {
        return PaymentResponse.builder()
                .id(paymentEntity.getId())
                .status(paymentEntity.getStatus())
                .paymentType(paymentEntity.getPaymentType())
                .createdAt(now())
                .build();
    }
}
