package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class RoundService {

    private RoundRepository roundRepository;
    private TurnService turnService;

    public RoundService(RoundRepository roundRepository, TurnService turnService){
        this.roundRepository = roundRepository;
        this.turnService = turnService;
    }

    public RoundE startRound(GameE game, Word word){
        RoundE round = new RoundE(word, game, new ArrayList<TurnE>());
        turnService.startTurn(round);
        game.addRound(round);
        roundRepository.save(round);
        return round;
    }

    public RoundE getRoundById(Long id){
        return roundRepository.findById(id).orElse(null);
    }
}
