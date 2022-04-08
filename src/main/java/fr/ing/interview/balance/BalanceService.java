package fr.ing.interview.balance;

import java.math.BigDecimal;

public interface BalanceService {

    BigDecimal retrieveBalance(String accountNumber);

}
