package fr.ing.interview.transaction.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

import static fr.ing.interview.commons.GlobalConstants.ACCOUNT_NUMBER_REGEXP;

@Validated
@RestController
@RequestMapping(value="/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<HistoryDTO> retrieveHistory(@PathVariable @Pattern(regexp = ACCOUNT_NUMBER_REGEXP) String accountNumber) {
        return ResponseEntity.ok().body(HistoryDTOMapper.toDTO(historyService.retrieveHistory(accountNumber)));
    }
}
