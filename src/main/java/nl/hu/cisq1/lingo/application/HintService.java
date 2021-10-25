package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.HintE;
import nl.hu.cisq1.lingo.data.HintRepository;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Hint;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HintService {

    private HintRepository hintRepository;
    private RoundService roundService;

    public HintService(HintRepository hintRepository, RoundService roundService){
        this.hintRepository = hintRepository;
        this.roundService = roundService;
    }

    public HintE giveHint(TurnE turnE, List<Character> hintList){
        if(hintList == null || turnE.getFeedback() == null){
            throw new NullPointerException();
        }

        List<Character> newList = new ArrayList<>(hintList);
        if(newList.isEmpty()) {
            newList.add(turnE.getRound().getWord().getValue().charAt(0));
            for (int i = 0; i < turnE.getRound().getWord().getValue().length() - 1; i++) {
                newList.add('.');
            }
            return new HintE(newList, turnE);
        }
        for(int i = 0; i < turnE.getFeedback().getMarks().size(); i++) {
            if (turnE.getFeedback().getMarks().get(i).equals(Mark.CORRECT) && newList.get(i).equals('.')) {
                newList.remove(i);
                newList.add(i, turnE.getFeedback().getAttempt().charAt(i));
            } else if (turnE.getFeedback().getMarks().get(i).equals(Mark.PRESENT)) {
                //later
            }
        }
        return new HintE(newList, turnE);
    }
}
