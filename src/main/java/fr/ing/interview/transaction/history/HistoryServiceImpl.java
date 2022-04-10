package fr.ing.interview.transaction.history;

import fr.ing.interview.model.HistoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService{

    private HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository repository) { this.historyRepository = repository; }

    @Override
    public List<HistoryEntity> retrieveHistory(String accountNumber) {
        return historyRepository.retrieveHistoryFromAccount(accountNumber);
    }
}
