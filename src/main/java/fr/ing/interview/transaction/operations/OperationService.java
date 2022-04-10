package fr.ing.interview.transaction.operations;

public interface OperationService {

    void withdraw(OperationDTO operationDTO);

    void deposit(OperationDTO operationDTO);

}
