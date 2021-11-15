package nl.hu.cisq1.lingo.trainer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hint")
public class Hint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "hint")
    private Turn turn;

    @ElementCollection
    private List<Character> hintList;

    public Hint(){}

    public Hint(List<Character> hintList, Turn turn){
        this.turn = turn;
        this.hintList = hintList;
    }

    public Hint giveHint(Turn turn, List<Character> hintList){
        if(hintList == null || turn.getFeedback() == null){
            throw new NullPointerException();
        }

        List<Character> newList = new ArrayList<>(hintList);
        if(newList.isEmpty()) {
            return provideFirstLetter(turn);
        }

        for(int i = 0; i < turn.getFeedback().getMarks().size(); i++) {
            if (turn.getFeedback().getMarks().get(i).equals(Mark.CORRECT) && newList.get(i).equals('.')) {
                newList.remove(i);
                newList.add(i, turn.getFeedback().getAttempt().charAt(i));
            } else if (turn.getFeedback().getMarks().get(i).equals(Mark.PRESENT)) {
                //later
            }
        }
        return new Hint(newList, turn);
    }


    public Hint provideFirstLetter(Turn turn){
        List<Character> newList = new ArrayList<>();
        newList.add(turn.getRound().getWord().getValue().charAt(0));
        for (int i = 0; i < turn.getRound().getWord().getValue().length() - 1; i++) {
            newList.add('.');
        }
        return new Hint(newList, turn);
    }

    @JsonIgnore
    public Turn getTurnE() {
        return turn;
    }

    public void setTurnE(Turn turn) {
        this.turn = turn;
    }

    public List<Character> getHintList() {
        return hintList;
    }

    public void setHintList(List<Character> hintList) {
        this.hintList = hintList;
    }
}
