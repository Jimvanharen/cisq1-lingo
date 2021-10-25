package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.*;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    private FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository = feedbackRepository;
    }

    public List<Mark> giveFeedback(List<Mark> prevFeedback, RoundE round, TurnE turn){
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

    public void saveFeedback(FeedbackE feedbackE){
        feedbackRepository.save(feedbackE);
    }
}
