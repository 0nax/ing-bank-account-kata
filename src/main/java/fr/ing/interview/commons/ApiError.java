package fr.ing.interview.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private String date;
    private HttpStatus status;
    private String message;
    private List errors;
}
