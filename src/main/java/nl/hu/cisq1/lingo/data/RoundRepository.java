package nl.hu.cisq1.lingo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoundRepository extends JpaRepository<RoundE, Long> {

    @Query("SELECT MAX(id) from RoundE")
    Optional<Long> maxId();

}
