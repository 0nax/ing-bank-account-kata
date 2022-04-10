package fr.ing.interview.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static fr.ing.interview.commons.GlobalConstants.CONSTRAINT_VALIDATION;
import static fr.ing.interview.commons.GlobalConstants.INVALID_AMOUNT;
import static fr.ing.interview.commons.GlobalConstants.RESOURCE_NOT_FOUND;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.List.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApiResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {

        ApiError error = new ApiError(
                now().format(ofPattern("yyy-MM-dd HH:mm:ss")),
                BAD_REQUEST,
                CONSTRAINT_VALIDATION,
                of(ex.getMessage()));

        return ApiResponseEntityBuilder.build(error);
    }

    @ExceptionHandler(ResourceNotFoundExcpetion.class)
    public ResponseEntity<?> handleResourceNotFoundExcpetion(ResourceNotFoundExcpetion ex) {

        ApiError error = new ApiError(
                now().format(ofPattern("yyy-MM-dd HH:mm:ss")),
                BAD_REQUEST,
                RESOURCE_NOT_FOUND,
                of(ex.getMessage()));

        return ApiResponseEntityBuilder.build(error);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<?> handleResourceInvalidOperationException(InvalidOperationException ex) {

        ApiError error = new ApiError(
                now().format(ofPattern("yyy-MM-dd HH:mm:ss")),
                BAD_REQUEST,
                INVALID_AMOUNT,
                of(ex.getMessage()));

        return ApiResponseEntityBuilder.build(error);
    }
}
