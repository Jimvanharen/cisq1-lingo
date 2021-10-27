package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Hint;

import javax.persistence.*;

@Entity
@Table(name = "turn")
public class TurnE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private FeedbackE feedback;

    @OneToOne(cascade = CascadeType.ALL)
    private HintE hint;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private RoundE round;

    @Column
    private int turnCount;
    private String guess;

    public TurnE(){}

    public TurnE(FeedbackE feedback, RoundE round, String guess, int turnCount, HintE hint){
        this.feedback = feedback;
        this.round = round;
        this.turnCount = turnCount;
        this.hint = hint;
        this.guess = guess;
    }
    public TurnE(String guess, int turnCount, RoundE round){
        this.round = round;
        this.turnCount = turnCount;
        this.guess = guess;
    }
    public TurnE(RoundE round){
        this.round = round;
    }

    public FeedbackE getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackE feedback) {
        this.feedback = feedback;
    }

    public HintE getHint() {
        return hint;
    }

    public void setHint(HintE hint) {
        this.hint = hint;
    }

    public RoundE getRound() {
        return round;
    }

    public void setRound(RoundE round) {
        this.round = round;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}
