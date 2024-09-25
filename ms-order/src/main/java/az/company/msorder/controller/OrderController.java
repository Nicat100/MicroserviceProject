package az.company.msorder.controller;

import az.company.msorder.model.request.CreateOrderRequest;
import az.company.msorder.model.response.OrderResponse;
import az.company.msorder.service.inter.OrderInter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderInter orderInter;

    @PostMapping()
    @ResponseStatus(CREATED)
    public void createOrder(@RequestBody CreateOrderRequest request){
        orderInter.createOrder(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public OrderResponse getOrderById(@PathVariable Long id){
        return orderInter.getOrderById(id);
    }
}
