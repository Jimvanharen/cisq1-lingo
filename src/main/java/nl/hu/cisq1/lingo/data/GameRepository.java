package nl.hu.cisq1.lingo.data;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT MAX(id) from Game")
    Optional<Long> maxId();
}
