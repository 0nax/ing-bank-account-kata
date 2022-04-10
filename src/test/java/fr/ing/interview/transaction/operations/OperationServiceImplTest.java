package fr.ing.interview.transaction.operations;


import fr.ing.interview.commons.InvalidOperationException;
import fr.ing.interview.model.AccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class OperationServiceImplTest {

    @Autowired
    private OperationServiceImpl operationService;

    @MockBean
    private OperationRepository operationRepository;

    @Test
    public void withdraw_negative_balance(){
        // Setup
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setAccountNumber("01234567891");
        operationDTO.setAmount(200);

        AccountEntity account = new AccountEntity();
        account.setAccountNumber("01234567891");
        account.setBalance(new BigDecimal("100"));
        when(operationRepository.retrieveAccount(operationDTO.getAccountNumber())).thenReturn(Optional.of(account));

        // Test
        Exception exception = assertThrows(InvalidOperationException.class, () -> operationService.withdraw(operationDTO));

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Operation not permited, insufficient balance");
    }

    @Test
    public void deposit_invalid_amount(){
        // Setup
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setAccountNumber("01234567891");
        operationDTO.setAmount(0.001);

        AccountEntity account = new AccountEntity();
        account.setAccountNumber("01234567891");
        account.setBalance(new BigDecimal("100"));
        when(operationRepository.retrieveAccount(operationDTO.getAccountNumber())).thenReturn(Optional.of(account));

        // Test
        Exception exception = assertThrows(InvalidOperationException.class, () -> operationService.deposit(operationDTO));

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Operation not permited, minimum deposit is 0,01");
    }

}
