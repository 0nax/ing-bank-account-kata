package fr.ing.interview.commons;

import org.springframework.http.ResponseEntity;

public class ApiResponseEntityBuilder {

    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
