package nl.hu.cisq1.lingo.trainer.domain;

import org.hibernate.service.spi.InjectService;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.List;

public class Hint {
    private List<Character> hintList;
    private Feedback feedback;

    public Hint(List<Character> hintList, Feedback feedback){
        this.hintList = hintList;
        this.feedback = feedback;
    }

    public Hint giveHint(){

        if(hintList == null || feedback == null){
            throw new NullPointerException();
        }

        List<Character> newList = new ArrayList<>(hintList);
        if(newList.isEmpty()) {
            newList.add(feedback.getAttempt().charAt(0));
            for (int i = 0; i < feedback.getAttempt().length() - 1; i++) {
                newList.add('.');
            }
            return new Hint(newList, feedback);
        }
        for(int i = 0; i < feedback.getMark().size(); i++) {
            if (feedback.getMark().get(i).equals(Mark.CORRECT) && newList.get(i).equals('.')) {
                newList.remove(i);
                newList.add(i, feedback.getAttempt().charAt(i));
            } else if (feedback.getMark().get(i).equals(Mark.PRESENT)) {
                //later
            }
        }
        return new Hint(newList, feedback);
    }

    public List<Character> getHintList() {
        return hintList;
    }

    public Feedback getFeedback() {
        return feedback;
    }
}