package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import java.util.Objects;

public class Feedback {

    private String attempt;
    private List<Mark> mark;

    public Feedback(String attempt, List<Mark> mark){
        this.attempt = attempt;
        this.mark = mark;
    }

    public boolean isWordGuessed(Feedback fb){
        for(Mark m : fb.mark){
            if(!m.equals(Mark.CORRECT)){
                return false;
            }
        }
        return true;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) &&
                Objects.equals(mark, feedback.mark);
    }

    public int hashCode() {
        return Objects.hash(attempt, mark);
    }

    public String getAttempt() {
        return attempt;
    }

    public List<Mark> getMark() {
        return mark;
    }

}
