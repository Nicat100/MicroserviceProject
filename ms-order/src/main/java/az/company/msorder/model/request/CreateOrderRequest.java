package az.company.msorder.model.request;

import az.company.msorder.model.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


import static az.company.msorder.constant.ApplicationConstant.*;

@Data
@Builder
public class CreateOrderRequest {
    @NotNull(message = PRODUCT_IS_REQUIRED)
    private Long productId;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;

    private PaymentType paymentType;
}
