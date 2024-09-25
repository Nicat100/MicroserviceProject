package az.company.mspayment.service.inter;

import az.company.mspayment.model.request.CreatePaymentRequest;
import az.company.mspayment.model.response.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface PaymentInter {
    PaymentResponse pay(CreatePaymentRequest request);

    PaymentResponse getPaymentByOrderId(Long orderId);
}
