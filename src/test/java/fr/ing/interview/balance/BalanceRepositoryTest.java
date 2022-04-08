package fr.ing.interview.balance;

import fr.ing.interview.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BalanceRepositoryTest {

    @Autowired
    private BalanceRepository balanceRepository;

    @Test
    public void existing_account_retrieve_balance(){
        //Setup
        String accountNumber = "01234567891";
        BigDecimal expectedBalance = new BigDecimal("100.50");

        // Test
        Optional<Account> account = balanceRepository.retrieveBalanceFromAccount(accountNumber);
        assertThat(account.isPresent()).isTrue();

        // Assert
        assertThat(expectedBalance).isEqualTo(account.get().getBalance());
    }

    @Test
    public void none_existing_account_dont_retrieve_balance(){
        //Setup
        String fakeAccountNumber = "0123";

        // Test
        Optional<Account> account = balanceRepository.retrieveBalanceFromAccount(fakeAccountNumber);

        // Assert
        assertThat(account.isEmpty()).isTrue();

    }
}
