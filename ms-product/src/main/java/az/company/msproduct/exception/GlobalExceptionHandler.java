package az.company.msproduct.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

import static az.company.msproduct.model.enums.ErrorMessage.SERVER_ERROR;
import static az.company.msproduct.model.enums.ErrorMessage.VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex,
                                HttpServletRequest request) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(InsufficientQuantityException ex,
                                HttpServletRequest request) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException ex,
                                HttpServletRequest request) {

        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach((error) -> errors.add(error.getDefaultMessage()));
        return ErrorResponse.builder()
                .message(VALIDATION_ERROR.getMessage())
                .path(request.getServletPath())
                .validationErrors(errors)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex) {
        return ErrorResponse.builder()
                .message(SERVER_ERROR.getMessage())
                .build();
    }
}
