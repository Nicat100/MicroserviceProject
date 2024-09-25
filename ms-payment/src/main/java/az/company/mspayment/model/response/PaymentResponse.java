package az.company.mspayment.model.response;

import az.company.mspayment.model.enums.PaymentStatus;
import az.company.mspayment.model.enums.PaymentType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private PaymentType paymentType;

}
