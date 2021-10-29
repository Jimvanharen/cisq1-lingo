package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.trainer.domain.Hint;
import nl.hu.cisq1.lingo.trainer.domain.Turn;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RoundService {

    private RoundRepository roundRepository;
    private TurnService turnService;

    public RoundService(RoundRepository roundRepository, TurnService turnService){
        this.roundRepository = roundRepository;
        this.turnService = turnService;
    }

    public RoundE startRound(GameE game, String toBeGuessedWord){
        RoundE round = new RoundE(new Word(toBeGuessedWord), game, new ArrayList<TurnE>());
        game.addRound(round);
        roundRepository.save(round);
        turnService.startTurn(round, "");
        return round;
    }

    public void removeRound(Long id){
        roundRepository.delete(Objects.requireNonNull(roundRepository.findById(id).orElse(null)));
    }

    public List<TurnE> getAllTurnsOfLastRound(){
        RoundE roundE = getRoundById(getRoundMaxId());
        System.out.println(roundE.getTurns());
        return roundE.getTurns();
    }

    public Long getRoundMaxId(){
        return roundRepository.maxId().orElse(null);
    }

    public RoundE getRoundById(Long id){
        return roundRepository.findById(id).orElse(null);
    }
}
