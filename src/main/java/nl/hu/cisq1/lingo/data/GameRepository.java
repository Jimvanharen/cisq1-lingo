package nl.hu.cisq1.lingo.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameRepository extends JpaRepository<GameE, Long> {

    @Query("SELECT MAX(id) from GameE")
    Optional<Long> maxId();
}
