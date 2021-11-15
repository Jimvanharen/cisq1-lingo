package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.trainer.domain.*;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TrainerService {

    private final TurnRepository turnRepository;
    private final RoundRepository roundRepository;
    private final GameRepository gameRepository;
    private final WordService wordService;
    private final FeedbackRepository feedbackRepository;

    public TrainerService(TurnRepository turnRepository,
                          RoundRepository roundRepository,
                          GameRepository gameRepository,
                          WordService wordService,
                          FeedbackRepository feedbackRepository) {
        this.turnRepository = turnRepository;
        this.roundRepository = roundRepository;
        this.gameRepository = gameRepository;
        this.wordService = wordService;
        this.feedbackRepository = feedbackRepository;
    }

    public Game startGame(){
        Game game = new Game(new ArrayList<>(), 0, GameStatus.PAUSED);
        game.addRound(startRound(game, wordService.provideRandomWord(5)));
        game.setGamestatus(GameStatus.PLAYING);
        gameRepository.save(game);
        return game;
    }

    public Game getGameById(Long id){
        return gameRepository.findById(id).orElse(null);
    }

    public Round startRound(Game game, String toBeGuessedWord){
        Round roundToStart = new Round().startRound(game, toBeGuessedWord);
        startTurn(roundToStart, "");
        roundRepository.save(roundToStart);
        return roundToStart;
    }

    public Turn startTurn(Round round, String guess) {
        Turn turn = new Turn(new Feedback(), new Hint(), round);
        turn.startTurn(round, guess);
        return turn;
    }

    public List<Turn> getAllTurnsOfLastRound(){
        Round round = getRoundById(getRoundMaxId());
        return round.getTurns();
    }

    public void removeRound(Long id){
        roundRepository.delete(Objects.requireNonNull(roundRepository.findById(id).orElse(null)));
    }

    public Long getRoundMaxId(){
        return roundRepository.maxId().orElse(null);
    }
    public Long getMaxId(){
        return gameRepository.maxId().orElse(null);
    }

    public Round getRoundById(Long id){
        return roundRepository.findById(id).orElse(null);
    }
}
