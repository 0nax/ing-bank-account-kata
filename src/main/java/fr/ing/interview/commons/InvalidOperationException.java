package fr.ing.interview.commons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidOperationException extends RuntimeException{

    private static final long serialVersionUID  = 1L;

    public InvalidOperationException(String errorMessage) { super(errorMessage); }
}
