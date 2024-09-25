package az.company.msorder.model.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReduceQuantityRequest {
    private Long productId;
    private Integer quantity;
}
