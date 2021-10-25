package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    private GameE gameE;

    @BeforeEach
    void before(){
        gameE = new GameE(null, 0, GameStatus.PAUSED);
    }

    @Test
    public void testStartGame(){
        GameService gameService = mock(GameService.class);
        RoundService roundService = mock(RoundService.class);
        when(gameService.startGame()).then((Answer<?>) roundService.startRound(gameE, "aardgas"));
    }

    @Test
    @DisplayName("Throw error when game not found")
    public void testGetGameById() throws Exception{
        WordService wordService = mock(WordService.class);
        RoundService roundService = mock(RoundService.class);
        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        GameService gameService = new GameService(gameRepository, wordService, roundService);

        assertThrows(
                Exception.class,
                () -> gameService.getGameById(200L));
    }
}
