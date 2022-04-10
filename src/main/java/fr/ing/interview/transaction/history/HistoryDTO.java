package fr.ing.interview.transaction.history;

import java.util.List;

public class HistoryDTO {

    List<Transaction> transactions;

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
