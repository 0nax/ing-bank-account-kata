package fr.ing.interview.transaction.operations;

import fr.ing.interview.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OperationRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT ACT FROM Account ACT where ACT.accountNumber = :accountNumber")
    Optional<Account> retrieveAccount(@Param("accountNumber") String accountNumber);

}
