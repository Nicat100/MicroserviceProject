package az.company.msproduct.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product not found with given id: %s"),
    INSUFFICIENT_QUANTITY("Not enough quantity with this id: %s"),
    VALIDATION_ERROR("Validation error !!! "),
    SERVER_ERROR("Unexpected error occurred !!! ");

    private final String message;
}
