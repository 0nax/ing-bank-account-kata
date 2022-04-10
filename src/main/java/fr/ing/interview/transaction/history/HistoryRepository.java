package fr.ing.interview.transaction.history;

import fr.ing.interview.model.HistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistoryRepository extends CrudRepository<HistoryEntity, Long> {

    @Query(value = "SELECT HIST FROM HistoryEntity HIST where HIST.accountNumber = :accountNumber")
    List<HistoryEntity> retrieveHistoryFromAccount(@Param("accountNumber") String accountNumber);

}
