package az.company.mspayment.controller;

import az.company.mspayment.model.request.CreatePaymentRequest;
import az.company.mspayment.model.response.PaymentResponse;
import az.company.mspayment.service.inter.PaymentInter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentInter paymentInter;

    @PostMapping
    @ResponseStatus(CREATED)
    public PaymentResponse pay(@RequestBody CreatePaymentRequest request){
        return paymentInter.pay(request);
    }

    @GetMapping("/order/{orderId}")
    @ResponseStatus(OK)
    public PaymentResponse getPaymentByOrderId(@PathVariable Long orderId){
        return paymentInter.getPaymentByOrderId(orderId);
    }
}
