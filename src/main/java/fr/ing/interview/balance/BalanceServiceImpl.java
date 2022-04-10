package fr.ing.interview.balance;

import fr.ing.interview.commons.ResourceNotFoundExcpetion;
import fr.ing.interview.model.AccountEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static fr.ing.interview.balance.BalanceConstants.ACCOUNT_NOT_FOUND;

@Service
public class BalanceServiceImpl implements BalanceService {

    private BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository repository) {
        this.balanceRepository = repository;
    }

    @Override
    public BigDecimal retrieveBalance(String accountNumber) {
        AccountEntity account = balanceRepository.retrieveBalanceFromAccount(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundExcpetion(ACCOUNT_NOT_FOUND));
        return account.getBalance();
    }
}
