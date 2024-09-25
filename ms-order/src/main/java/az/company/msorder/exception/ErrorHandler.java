package az.company.msorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static az.company.msorder.model.enums.ErrorMessages.SERVER_ERROR;
import static az.company.msorder.model.enums.ErrorMessages.VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException ex) {
       List<String> errors = new ArrayList<>();

       ex.getBindingResult()
               .getAllErrors()
               .forEach(
                       (error) -> errors.add(
                               error.getDefaultMessage()
                       )
               );

       return ErrorResponse.builder()
               .message("Validation error")
               .validationErrors(errors)
               .build();

    }

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ErrorResponse> handle(CustomFeignException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex) {
        return ErrorResponse.builder()
                .message(SERVER_ERROR.getMessage())
                .build();
    }










}
