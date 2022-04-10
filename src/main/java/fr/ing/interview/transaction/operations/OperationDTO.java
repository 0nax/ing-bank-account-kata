package fr.ing.interview.transaction.operations;

import lombok.Setter;

import javax.validation.constraints.Pattern;

import static fr.ing.interview.commons.GlobalConstants.ACCOUNT_NUMBER_REGEXP;

@Setter
public class OperationDTO {

    private String accountNumber;
    private double amount;

    @Pattern(regexp = ACCOUNT_NUMBER_REGEXP)
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
