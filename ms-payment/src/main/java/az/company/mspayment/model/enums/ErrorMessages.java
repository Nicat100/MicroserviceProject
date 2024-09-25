package az.company.mspayment.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@Getter
public enum ErrorMessages {
    PAYMENT_NOT_FOUND("Payment not found with given id: %s"),
    SERVER_ERROR("Unexpected error occurred");

    private final String message;
}
