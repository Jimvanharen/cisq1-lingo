package nl.hu.cisq1.lingo.trainer.domain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Turn {

    private Feedback feedback;
    private Round round;
    private int turnCount;

    public Turn(Feedback feedback, Round round, int turnCount){
        this.feedback = feedback;
        this.round = round;
        if(!(turnCount > Round.getMaxAttempts())){
            this.turnCount = turnCount;
        }
    }

    public void startTurn(String attempt){

    }

    public List<Mark> turnFeedback(List<Mark> prevFeedback){
        List<Mark> newFeedback = new ArrayList<>(feedback.getAttempt().length());
        if(turnCount > 1 && !prevFeedback.isEmpty()){
            newFeedback = prevFeedback;
        }

        //Check for all invalid
        if(round.getWord().getValue().length() > feedback.getAttempt().length() || round.getWord().getValue().length() < feedback.getAttempt().length()){
            for(int i = 0; i < feedback.getAttempt().length(); i++){
                newFeedback.add(i, Mark.INVALID);
            }
            return newFeedback;
        }
        //Check for all Correct
        if(feedback.getAttempt().equals(round.getWord().getValue())){
            for(int i = 0; i < feedback.getAttempt().length(); i++){
                newFeedback.add(i, Mark.CORRECT);
                round.getGame().setScore(round.getGame().getScore() + (5 * (5 - turnCount) + 5));
            }
            return newFeedback;
        }
        //Check for CORRECT and PRESENT and ABSENT
            for(int i = 0; i < feedback.getAttempt().length(); i++){
                if(round.getWord().getValue().charAt(i) == feedback.getAttempt().charAt(i)){
                    newFeedback.add(Mark.CORRECT);
                }
                else if(round.getWord().getValue().charAt(i) != feedback.getAttempt().charAt(i)){
                    if(round.getWord().getValue().contains(
                            (Character.toString(feedback.getAttempt().charAt(i))))){
                        newFeedback.add(Mark.PRESENT);
                    }
                    else if(!round.getWord().getValue().contains(
                            (Character.toString(feedback.getAttempt().charAt(i))))){
                        newFeedback.add(Mark.ABSENT);
                    }
                }
            }
        return newFeedback;
    }

    public int getTurnCount() {
        return turnCount;
    }
}
