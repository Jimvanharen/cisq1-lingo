package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.Generated;
import nl.hu.cisq1.lingo.trainer.domain.exception.TurnCountException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turn")
public class Turn {

    private static final int TURN_BOUNDARY = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private Feedback feedback;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hint_id", referencedColumnName = "id")
    private Hint hint;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @Column
    private int turnCount;
    private String guess;

    public Turn(){}

    public Turn(Feedback feedback, Round round, String guess, int turnCount, Hint hint){
        this.feedback = feedback;
        this.round = round;
        this.turnCount = turnCount;
        this.hint = hint;
        this.guess = guess;
    }
    public Turn(String guess, int turnCount, Round round){
        this.round = round;
        this.turnCount = turnCount;
        this.guess = guess;
    }
    public Turn(Feedback feedback, Hint hint ,Round round){
        this.round = round;
        this.feedback = feedback;
        this.hint = hint;
    }
    public Turn(Round round){
        this.round = round;
    }

    public Turn startTurn(Round round, String guess) {
        if(round.getTurns().size() >= TURN_BOUNDARY){
            throw new TurnCountException();
        }

        Turn turn = new Turn(guess, round.getTurns().size(), round);
        List<Mark> feedbackOfMarks = feedback.giveFeedback(new ArrayList<>(), round, turn);

        Feedback feedback = new Feedback(feedbackOfMarks, turn, guess);
        turn.setFeedback(feedback);
        turn.setHint(hint.giveHint(turn, giveLastHintList(round)));
        round.addTurn(turn);

        return turn;
    }

    @Generated
    private List<Character> giveLastHintList(Round round){
        Turn turn;
        if(round.getTurns().size() >= 1){
            turn = round.getTurns().get(round.getTurns().size() - 1);
            return turn.getHint().getHintList();
        }
        else{
            return new ArrayList<>();
        }
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Hint getHint() {
        return hint;
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
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
