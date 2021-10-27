package nl.hu.cisq1.lingo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TurnRepository extends JpaRepository<TurnE, Long> {

    @Query("SELECT MAX(id) from TurnE")
    Optional<Long> maxId();

    Optional<TurnE> findByRoundId(Long id);
}
