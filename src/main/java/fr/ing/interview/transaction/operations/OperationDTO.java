package fr.ing.interview.transaction.operations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static fr.ing.interview.commons.GlobalConstants.ACCOUNT_NUMBER_REGEXP;

@Setter
public class OperationDTO {

    private String accountNumber;
    private int amount;

    @Pattern(regexp = ACCOUNT_NUMBER_REGEXP)
    public String getAccountNumber() {
        return accountNumber;
    }

    @Min(value = 10) @NotNull
    public int getAmount() {
        return amount;
    }
}
