package fr.ing.interview.transaction.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value="/operation")
public class OperationsController {

    @Autowired
    private OperationService operationService;

    @PostMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> withdraw(@RequestBody @Validated OperationDTO operationDTO) {
        operationService.withdraw(operationDTO);
        return ResponseEntity.ok().body("withdraw successful");
    }

    @PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deposit(@RequestBody @Validated OperationDTO operationDTO) {
        operationService.deposit(operationDTO);
        return ResponseEntity.ok().body("deposit successful");
    }
}
