package fr.ing.interview.balance;

import fr.ing.interview.commons.ResourceNotFoundExcpetion;
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
class BalanceServiceImplTest {

    @Autowired
    private BalanceServiceImpl balanceService;

    @MockBean
    private BalanceRepository balanceRepository;

    @Test
    public void account_found_return_balance(){
        // Setup
        String accountNumber = "01234567891";
        AccountEntity account = new AccountEntity("1", new BigDecimal("100"), accountNumber);
        when(balanceRepository.retrieveBalanceFromAccount(accountNumber)).thenReturn(Optional.of(account));

        // Test
        BigDecimal balance = balanceService.retrieveBalance(accountNumber);

        // Assert
        assertThat(balance).isEqualTo("100");
    }

    @Test
    public void acount_not_found_return_exception(){
        // Setup
        String accountNumber = "01234567891";
        when(balanceRepository.retrieveBalanceFromAccount(accountNumber)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundExcpetion.class, () -> balanceService.retrieveBalance(accountNumber));

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Account not found");
    }

}
