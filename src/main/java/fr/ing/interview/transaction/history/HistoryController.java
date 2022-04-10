package fr.ing.interview.transaction.history;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Validated
@RestController
@RequestMapping(value="/history")
public class HistoryController {

    @GetMapping("/{accountNumber}")
    public ResponseEntity<HistoryDTO> retrieveHistory(@PathVariable String accountNumber) {
        return ResponseEntity.ok().body(new HistoryDTO());
    }
}
