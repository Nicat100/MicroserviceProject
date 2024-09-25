package az.company.msproduct.exception;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(String message, String path, List<String> validationErrors) {
}
