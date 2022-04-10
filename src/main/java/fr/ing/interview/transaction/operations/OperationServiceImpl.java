package fr.ing.interview.transaction.operations;

import fr.ing.interview.balance.BalanceRepository;
import fr.ing.interview.commons.InvalidOperationException;
import fr.ing.interview.commons.ResourceNotFoundExcpetion;
import fr.ing.interview.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static fr.ing.interview.balance.BalanceConstants.ACCOUNT_NOT_FOUND;
import static fr.ing.interview.transaction.operations.OperationConstants.MINIMUM_DEPOST;
import static java.math.BigDecimal.valueOf;

@Service
public class OperationServiceImpl implements OperationService{

    private OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository repository) {
        this.operationRepository = repository;
    }

    @Override
    public void withdraw(OperationDTO operationDTO) {
        Account account = operationRepository.retrieveAccount(operationDTO.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundExcpetion(ACCOUNT_NOT_FOUND));

        BigDecimal newBalance = account.getBalance().subtract(valueOf(operationDTO.getAmount()));
        if (newBalance.compareTo(new BigDecimal("0")) > 0 ){
            account.setBalance(newBalance);
            operationRepository.save(account);
        } else {
            throw new InvalidOperationException("Operation not permited, insufficient balance");
        }

    }

    @Override
    public void deposit(OperationDTO operationDTO) {
        Account account = operationRepository.retrieveAccount(operationDTO.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundExcpetion(ACCOUNT_NOT_FOUND));

        if (operationDTO.getAmount() > MINIMUM_DEPOST){
            BigDecimal newBalance = account.getBalance().add(valueOf(operationDTO.getAmount()));
            account.setBalance(newBalance);
            operationRepository.save(account);
        } else {
            throw new InvalidOperationException("Operation not permited, minimum deposit is 0,01");
        }

    }
}
