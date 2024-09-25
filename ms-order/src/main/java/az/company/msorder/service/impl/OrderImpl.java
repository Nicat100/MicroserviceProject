package az.company.msorder.service.impl;

import az.company.msorder.client.PaymentClient;
import az.company.msorder.client.ProductClient;
import az.company.msorder.dao.repository.OrderRepository;
import az.company.msorder.exception.NotFoundException;
import az.company.msorder.model.client.request.ReduceQuantityRequest;
import az.company.msorder.model.request.CreateOrderRequest;
import az.company.msorder.model.response.OrderResponse;
import az.company.msorder.service.inter.OrderInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static az.company.msorder.mapper.OrderMapper.ORDER_MAPPER;
import static az.company.msorder.mapper.PaymentMapper.PAYMENT_MAPPER;
import static az.company.msorder.model.enums.ErrorMessages.ORDER_NOT_FOUND;
import static az.company.msorder.model.enums.OrderStatus.*;
import static az.company.msorder.model.enums.OrderStatus.APPROVED;
import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderInter {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest orderRequest) {
        var orderEntity = ORDER_MAPPER.mapToEntity(orderRequest);
        var productResponse = productClient.getProductById(orderRequest.getProductId());
        var totalAmount = productResponse.getPrice()
                .multiply(BigDecimal.valueOf(orderRequest.getQuantity()));

        orderEntity.setAmount(totalAmount);

        var reduceQuantityRequest = new ReduceQuantityRequest(
                orderRequest.getProductId(),
                orderRequest.getQuantity()
        );

        orderRepository.save(orderEntity);
        productClient.reduceQuantity(reduceQuantityRequest);

        try {
            paymentClient.pay(
                    PAYMENT_MAPPER.mapToCreatePaymentRequest(orderEntity,
                            orderRequest,
                            totalAmount)
            );
            orderEntity.setStatus(APPROVED);
        } catch (Exception e) {
            orderEntity.setStatus(REJECTED);
        }
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        var orderEntity = orderRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                format(ORDER_NOT_FOUND.getMessage(),
                                        id
                                )));

       var paymentResponse =paymentClient.getPaymentByOrderId(orderEntity.getId());

        var productResponse = productClient.getProductById(orderEntity.getProductId());
        return ORDER_MAPPER.maoToResponse(orderEntity, productResponse,paymentResponse);
    }
}
