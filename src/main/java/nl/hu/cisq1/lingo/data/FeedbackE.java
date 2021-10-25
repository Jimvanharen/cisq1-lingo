package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feedback")
public class FeedbackE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Mark> marks;

    @OneToOne
    private TurnE turnE;

    @Column
    private String attempt;

    public FeedbackE(){}

    public FeedbackE(List<Mark> marks, TurnE turnE, String attempt){
        this.marks = marks;
        this.turnE = turnE;
        this.attempt = attempt;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public TurnE getTurnE() {
        return turnE;
    }

    public void setTurnE(TurnE turnE) {
        this.turnE = turnE;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }
}
