package az.company.msorder.service.inter;

import az.company.msorder.model.request.CreateOrderRequest;
import az.company.msorder.model.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderInter {
    void createOrder(CreateOrderRequest createOrderRequest);

    OrderResponse getOrderById(Long id);

}
