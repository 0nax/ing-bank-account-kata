package fr.ing.interview.transaction.history;

import fr.ing.interview.model.HistoryEntity;

import java.util.ArrayList;
import java.util.List;

public class HistoryDTOMapper {

    public static HistoryDTO toDTO(List<HistoryEntity> history){
        HistoryDTO historyDTO = new HistoryDTO();
        List<Transaction> transactions = new ArrayList<>();

        for (HistoryEntity historyEntity : history) {
            transactions.add(new Transaction(historyEntity.getDate(), historyEntity.getTransactionType(), historyEntity.getAmount()));
        }

        historyDTO.setTransactions(transactions);
        return historyDTO;
    }
}
