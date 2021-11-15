package nl.hu.cisq1.lingo.trainer.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Mark> marks;

    @OneToOne(mappedBy = "feedback")
    private Turn turn;

    @Column
    private String attempt;

    public Feedback(){}

    public Feedback(List<Mark> marks, Turn turn, String attempt){
        this.marks = marks;
        this.turn = turn;
        this.attempt = attempt;
    }

    public List<Mark> giveFeedback(List<Mark> prevFeedback, Round round, Turn turn){
        List<Mark> newFeedback = new ArrayList<>(turn.getGuess().length());
        if(turn.getTurnCount() > 1 && !prevFeedback.isEmpty()){
            newFeedback = prevFeedback;
        }

        //Check for all invalid
        if(round.getWord().getValue().length() > turn.getGuess().length() || round.getWord().getValue().length() < turn.getGuess().length()){
            for(int i = 0; i < turn.getGuess().length(); i++){
                newFeedback.add(i, Mark.INVALID);
            }
            return newFeedback;
        }
        //Check for all Correct
        if(turn.getGuess().equals(round.getWord().getValue())){
            for(int i = 0; i < turn.getGuess().length(); i++){
                newFeedback.add(i, Mark.CORRECT);
                round.getGame().setScore(round.getGame().getScore() + (5 * (5 - turn.getTurnCount()) + 5));
            }
            return newFeedback;
        }
        //Check for CORRECT and PRESENT and ABSENT
        for(int i = 0; i < turn.getGuess().length(); i++){
            if(round.getWord().getValue().charAt(i) == turn.getGuess().charAt(i)){
                newFeedback.add(Mark.CORRECT);
            }
            else if(round.getWord().getValue().charAt(i) != turn.getGuess().charAt(i)){
                if(round.getWord().getValue().contains(
                        (Character.toString(turn.getGuess().charAt(i))))){
                    newFeedback.add(Mark.PRESENT);
                }
                else if(!round.getWord().getValue().contains(
                        (Character.toString(turn.getGuess().charAt(i))))){
                    newFeedback.add(Mark.ABSENT);
                }
            }
        }
        return newFeedback;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    @JsonIgnore
    public Turn getTurnE() {
        return turn;
    }

    public void setTurnE(Turn turn) {
        this.turn = turn;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }
}
