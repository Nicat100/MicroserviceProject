package az.company.mspayment.exception;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(String message, List<String> validationErrors) {
}
