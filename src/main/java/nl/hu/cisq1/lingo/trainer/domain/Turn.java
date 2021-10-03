package nl.hu.cisq1.lingo.trainer.domain;

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

    public List<Mark> turnFeedback(){
        List<Mark> newFeedback = new ArrayList<>();
        if(turnCount > 1){
            //newFeedback = previousFeedback;
        }

        if(round.getWord().getToBeGuessedWord().length() > feedback.getAttempt().length() || round.getWord().getToBeGuessedWord().length() < feedback.getAttempt().length()){
            for(int i = 0; i < feedback.getAttempt().length(); i++){
                newFeedback.add(i, Mark.INVALID);
            }
        }

        if(feedback.getAttempt().equals(round.getWord().getToBeGuessedWord())){
            for(int i = 0; i < feedback.getAttempt().length(); i++){
                newFeedback.add(i, Mark.CORRECT);
                round.getGame().setScore(round.getGame().getScore() + (5 * (5 - turnCount) + 5));
            }
            newFeedback.forEach(System.out::println);
            return newFeedback;
        }
        //CHECK FOR ABSENT
        else if(!feedback.getAttempt().equals(round.getWord().getToBeGuessedWord()) &&
                !round.getWord().getToBeGuessedWord().contains(feedback.getAttempt())){
            for(int i = 0; i < feedback.getAttempt().length(); i++){
                newFeedback.add(i, Mark.ABSENT);
            }
            newFeedback.forEach(System.out::println);
            return newFeedback;
        }

        //Check for CORRECT and PRESENT
        else if(round.getWord().getToBeGuessedWord().contains(feedback.getAttempt())){
            for(int i = 0; i < feedback.getAttempt().length(); i++){
                if(round.getWord().getToBeGuessedWord().charAt(i) == feedback.getAttempt().charAt(i)){
                    newFeedback.remove(i);
                    newFeedback.add(i, Mark.CORRECT);
                }
                else if(round.getWord().getToBeGuessedWord().charAt(i) != feedback.getAttempt().charAt(i)){
                    if(round.getWord().getToBeGuessedWord().contains
                            (Character.toString(feedback.getAttempt().charAt(i)))){
                        newFeedback.remove(i);
                        newFeedback.add(i, Mark.PRESENT);
                    }
                    else{
                        newFeedback.remove(i);
                        newFeedback.add(i, Mark.ABSENT);
                    }
                }
            }
        }

        return newFeedback;
    }
}
