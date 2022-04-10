package fr.ing.interview.transaction.operations;


import fr.ing.interview.commons.InvalidOperationException;
import fr.ing.interview.model.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OperationServiceImplTest {

    @Autowired
    private OperationServiceImpl operationService;

    @MockBean
    private OperationRepository operationRepository;

    @Test
    public void negative_balance(){
        // Setup
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setAccountNumber("01234567891");
        operationDTO.setAmount(200);

        Account account = new Account();
        account.setAccountNumber("01234567891");
        account.setBalance(new BigDecimal("100"));
        Mockito.when(operationRepository.retrieveAccount(operationDTO.getAccountNumber())).thenReturn(Optional.of(account));

        // Test
        Exception exception = assertThrows(InvalidOperationException.class, () -> operationService.withdraw(operationDTO));

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Operation not permited, insufficient balance");
    }

}
