package az.company.msorder.client;

import az.company.msorder.client.decoder.CustomErrorDecoder;
import az.company.msorder.model.client.request.ReduceQuantityRequest;
import az.company.msorder.model.client.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "ms-product",
        url = "http://localhost:9090/api/v1/products",
        configuration = CustomErrorDecoder.class
)
public interface ProductClient {

    @PostMapping("/reduce-quantity")
    void reduceQuantity(@RequestBody ReduceQuantityRequest reduceQuantityRequest);

    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
