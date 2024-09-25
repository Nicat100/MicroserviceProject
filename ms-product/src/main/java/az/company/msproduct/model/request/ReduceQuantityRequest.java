package az.company.msproduct.model.request;

import az.company.msproduct.constant.ApplicationConstant;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static az.company.msproduct.constant.ApplicationConstant.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReduceQuantityRequest {
    @NotNull(message = PRODUCT_ID_IS_REQUIRED)
    private Long productId;
    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;
}
