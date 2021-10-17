package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.stereotype.Service;
import static org.mockito.Mockito.*;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class GameService {

    private GameRepository gameRepository;
    private WordService wordService;
    private RoundService roundService;

    public GameService(GameRepository gameRepository, WordService wordService, RoundService roundService){
        this.gameRepository = gameRepository;
        this.wordService = wordService;
        this.roundService = roundService;
    }

    public void startGame(){
        GameE game = new GameE(new ArrayList<RoundE>(), 0);
        roundService.startRound(game, new Word(wordService.provideRandomWord(5)));
        gameRepository.save(game);
    }

}
