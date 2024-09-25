package az.company.mspayment.service.impl;

import az.company.mspayment.dao.repository.PaymentRepository;
import az.company.mspayment.exception.NotFoundException;
import az.company.mspayment.model.request.CreatePaymentRequest;
import az.company.mspayment.model.response.PaymentResponse;
import az.company.mspayment.service.inter.PaymentInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static az.company.mspayment.mapper.PaymentMapper.PAYMENT_MAPPER;
import static az.company.mspayment.model.enums.ErrorMessages.PAYMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PaymentImpl implements PaymentInter {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse pay(CreatePaymentRequest request) {
        var paymentEntity = PAYMENT_MAPPER.mapToPaymentEntity(request);
        paymentRepository.save(paymentEntity);

        return PAYMENT_MAPPER.mapToPaymentResponse(paymentEntity);
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(PAYMENT_MAPPER::mapToPaymentResponse)
                .orElseThrow(
                        () -> new NotFoundException(
                                PAYMENT_NOT_FOUND.getMessage() +
                                        orderId
                        )
                );
    }
}
