package az.company.msorder.model.response;

import az.company.msorder.model.client.response.PaymentResponse;
import az.company.msorder.model.client.response.ProductResponse;
import az.company.msorder.model.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
    private OrderStatus status;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private ProductResponse product;
    private PaymentResponse payment;
}
