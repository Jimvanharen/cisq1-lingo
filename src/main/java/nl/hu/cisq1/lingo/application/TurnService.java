package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.*;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.Turn;
import nl.hu.cisq1.lingo.trainer.domain.exception.TurnCountException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnService {

    private final TurnRepository turnRepository;
    private final HintService hintService;
    private final FeedbackService feedbackService;

    private static final int TURN_BOUNDARY = 5;

    public TurnService(TurnRepository turnRepository, HintService hintService, FeedbackService feedbackService){
        this.turnRepository = turnRepository;
        this.hintService = hintService;
        this.feedbackService = feedbackService;
    }

    public TurnE startTurn(RoundE roundE, String guess){
        if(roundE.getTurns().size() >= TURN_BOUNDARY){
            throw new TurnCountException();
        }

        TurnE turn = new TurnE(guess, roundE.getTurns().size(), roundE);
        List<Mark> feedbackOfMarks = feedbackService.giveFeedback(new ArrayList<>(), roundE, turn);

        FeedbackE feedbackE = new FeedbackE(feedbackOfMarks, turn, guess);
        turn.setFeedback(feedbackE);
        turn.setHint(hintService.giveHint(turn, giveLastHintList(roundE)));
        roundE.addTurn(turn);
        turnRepository.save(turn);

        return turn;
    }

    private List<Character> giveLastHintList(RoundE roundE){
        TurnE turnE;
        if(roundE.getTurns().size() > 1){
            turnE = roundE.getTurns().get(roundE.getTurns().size() - 1);
            return turnE.getHint().getHintList();
        }
        else{
            return new ArrayList<>();

        }
    }

    public TurnE getTurnById(Long id){
        return turnRepository.findById(id).orElse(null);
    }
}
