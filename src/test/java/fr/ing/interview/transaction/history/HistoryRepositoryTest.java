package fr.ing.interview.transaction.history;

import fr.ing.interview.model.HistoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class HistoryRepositoryTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void retrieve_history(){
        // Setup
        String accountNumber = "01234567891";

        // Test
        List<HistoryEntity> historyEntities = historyRepository.retrieveHistoryFromAccount(accountNumber);

        // Assert
        assertThat(historyEntities).isNotEmpty();
    }


}
