package fr.ing.interview.transaction.operations;

import fr.ing.interview.commons.InvalidOperationException;
import fr.ing.interview.commons.ResourceNotFoundExcpetion;
import fr.ing.interview.model.AccountEntity;
import fr.ing.interview.model.HistoryEntity;
import fr.ing.interview.model.TransactionType;
import fr.ing.interview.transaction.history.HistoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static fr.ing.interview.balance.BalanceConstants.ACCOUNT_NOT_FOUND;
import static fr.ing.interview.model.TransactionType.DEPOSIT;
import static fr.ing.interview.model.TransactionType.WITHDRAW;
import static fr.ing.interview.transaction.operations.OperationConstants.MINIMUM_DEPOST;
import static java.math.BigDecimal.valueOf;

@Service
public class OperationServiceImpl implements OperationService{

    private OperationRepository operationRepository;
    private HistoryRepository historyRepository;

    public OperationServiceImpl(OperationRepository operationRepository, HistoryRepository historyRepository) {
        this.operationRepository = operationRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public void withdraw(OperationDTO operationDTO) {
        AccountEntity account = operationRepository.retrieveAccount(operationDTO.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundExcpetion(ACCOUNT_NOT_FOUND));

        BigDecimal newBalance = account.getBalance().subtract(valueOf(operationDTO.getAmount()));
        if (newBalance.compareTo(new BigDecimal("0")) > 0 ){
            account.setBalance(newBalance);
            operationRepository.save(account);
            historyRepository.save(createHistoryEntity(operationDTO, WITHDRAW));
        } else {
            throw new InvalidOperationException("Operation not permited, insufficient balance");
        }

    }

    @Override
    public void deposit(OperationDTO operationDTO) {
        AccountEntity account = operationRepository.retrieveAccount(operationDTO.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundExcpetion(ACCOUNT_NOT_FOUND));

        if (operationDTO.getAmount() > MINIMUM_DEPOST){
            BigDecimal newBalance = account.getBalance().add(valueOf(operationDTO.getAmount()));
            account.setBalance(newBalance);
            operationRepository.save(account);
            historyRepository.save(createHistoryEntity(operationDTO, DEPOSIT));
        } else {
            throw new InvalidOperationException("Operation not permited, minimum deposit is 0,01");
        }
    }

    private HistoryEntity createHistoryEntity(OperationDTO operationDTO, TransactionType withdraw) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setAccountNumber(operationDTO.getAccountNumber());
        historyEntity.setTransactionType(withdraw);
        historyEntity.setAmount(operationDTO.getAmount());
        historyEntity.setDate(LocalDateTime.now());
        return historyEntity;
    }
}
