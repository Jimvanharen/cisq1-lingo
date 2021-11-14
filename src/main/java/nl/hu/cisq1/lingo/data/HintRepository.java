package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.trainer.domain.Hint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HintRepository extends JpaRepository<Hint, Long> {
}
