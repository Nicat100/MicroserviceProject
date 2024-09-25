package az.company.msorder.client;

import az.company.msorder.client.decoder.CustomErrorDecoder;
import az.company.msorder.model.client.request.CreatePaymentRequest;
import az.company.msorder.model.client.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = "ms-payment",
        url = "http://localhost:8082/v1/payments",
        configuration = CustomErrorDecoder.class
)
public interface PaymentClient {
    @PostMapping
    void pay(@RequestBody CreatePaymentRequest request);

    @GetMapping("/order/{orderId}")
    PaymentResponse getPaymentByOrderId(@PathVariable Long orderId);

}
