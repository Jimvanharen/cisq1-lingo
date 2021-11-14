package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.trainer.domain.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TurnRepository extends JpaRepository<Turn, Long> {

    @Query("SELECT MAX(id) from Turn")
    Optional<Long> maxId();

    Optional<Turn> findByRoundId(Long id);
}
