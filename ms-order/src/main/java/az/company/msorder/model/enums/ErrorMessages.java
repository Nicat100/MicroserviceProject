package az.company.msorder.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    ORDER_NOT_FOUND("Order not found with given id: %s"),
    CLIENT_ERROR("Client error occurred while making the request"),
    SERVER_ERROR("Unexpected error occurred"),
    VALIDATION_ERROR("Validation error");

    private final String message;
}
