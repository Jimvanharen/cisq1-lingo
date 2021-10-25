package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.*;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.Turn;
import nl.hu.cisq1.lingo.trainer.domain.exception.TurnCountException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnService {

    private TurnRepository turnRepository;
    private HintService hintService;
    private FeedbackService feedbackService;

    public TurnService(TurnRepository turnRepository, HintService hintService, FeedbackService feedbackService){
        this.turnRepository = turnRepository;
        this.hintService = hintService;
        this.feedbackService = feedbackService;
    }

    public TurnE startTurn(RoundE roundE, String guess){
        if(roundE.getTurns().size() >= 5){
            throw new TurnCountException();
        }

        TurnE turn = new TurnE(guess, roundE.getTurns().size(), roundE);
        List<Mark> feedbackOfMarks = feedbackService.giveFeedback(new ArrayList<>(), roundE, turn);

        for(Mark m : feedbackOfMarks){
            System.out.println(m);
        }

        FeedbackE feedbackE = new FeedbackE(feedbackOfMarks, turn, guess);
        turn.setFeedback(feedbackE);
        turn.setHint(hintService.giveHint(turn, new ArrayList<>()));
        roundE.addTurn(turn);
        turnRepository.save(turn);

        return turn;
    }

    private List<Character> giveLastHintList(RoundE roundE){
        TurnE turnE;
        if(roundE.getTurns().size() > 1){
            turnE = getTurnById(roundE.getTurns().size() - 1L);
            return turnE.getHint().getHintList();
        }
        return new ArrayList<>();
    }

    public TurnE getTurnById(Long id){
        return turnRepository.findById(id).orElse(null);
    }
}
