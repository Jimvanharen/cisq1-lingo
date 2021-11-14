package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.trainer.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoundRepository extends JpaRepository<Round, Long> {

    @Query("SELECT MAX(id) from Round")
    Optional<Long> maxId();

}
