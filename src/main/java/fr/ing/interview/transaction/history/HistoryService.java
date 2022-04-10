package fr.ing.interview.transaction.history;

import fr.ing.interview.model.HistoryEntity;

import java.util.List;

public interface HistoryService {

    List<HistoryEntity> retrieveHistory(String accountNumber);
}
