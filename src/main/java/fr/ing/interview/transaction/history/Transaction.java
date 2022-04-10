package fr.ing.interview.transaction.history;

import fr.ing.interview.model.TransactionType;

import java.time.LocalDateTime;

public class Transaction {

    private LocalDateTime date;
    private TransactionType transactionType;
    private double amount;

    public Transaction (LocalDateTime date, TransactionType transactionType, double amount){
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}
