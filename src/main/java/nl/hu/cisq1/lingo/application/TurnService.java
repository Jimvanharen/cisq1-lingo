package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.data.TurnRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurnService {

    private TurnRepository turnRepository;

    public TurnService(TurnRepository turnRepository){
        this.turnRepository = turnRepository;
    }

    public TurnE startTurn(RoundE roundE){
        TurnE turn = new TurnE();
        roundE.addTurn(turn);
        turnRepository.save(turn);
        return turn;
    }

    public TurnE getTurnById(Long id){
        return turnRepository.findById(id).orElse(null);
    }
}
