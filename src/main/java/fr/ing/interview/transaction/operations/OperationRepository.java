package fr.ing.interview.transaction.operations;

import fr.ing.interview.model.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OperationRepository extends CrudRepository<AccountEntity, Long> {

    @Query(value = "SELECT ACT FROM AccountEntity ACT where ACT.accountNumber = :accountNumber")
    Optional<AccountEntity> retrieveAccount(@Param("accountNumber") String accountNumber);

}
