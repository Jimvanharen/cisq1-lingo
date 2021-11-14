package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
