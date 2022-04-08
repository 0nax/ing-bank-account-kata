package fr.ing.interview.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.constraints.Pattern;

import java.math.BigDecimal;

import static fr.ing.interview.balance.BalanceConstants.ACCOUNT_NUMBER_REGEXP;

@Validated
@RestController
@RequestMapping(value="/consulting")
public class BalanceController {

    @Autowired
    private BalanceService consultingService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BigDecimal> consultAccount(@PathVariable @Pattern(regexp = ACCOUNT_NUMBER_REGEXP) String accountNumber){
        return ResponseEntity.ok(consultingService.retrieveBalance(accountNumber));
    }
}
