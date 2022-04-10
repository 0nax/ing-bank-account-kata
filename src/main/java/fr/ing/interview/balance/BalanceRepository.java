package fr.ing.interview.balance;

import fr.ing.interview.model.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends CrudRepository<AccountEntity, Long> {
    @Query(value = "SELECT ACT FROM AccountEntity ACT where ACT.accountNumber = :accountNumber")
    Optional<AccountEntity> retrieveBalanceFromAccount(@Param("accountNumber") String accountNumber);

}
