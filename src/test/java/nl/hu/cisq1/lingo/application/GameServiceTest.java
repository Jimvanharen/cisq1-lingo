package nl.hu.cisq1.lingo.application;

import java.util.ArrayList;

import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GameService.class, RoundService.class, WordService.class})
@ExtendWith(SpringExtension.class)
public class GameServiceTest {

    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @MockBean
    private RoundService roundService;

    @MockBean
    private WordService wordService;

    private GameE gameE;

    @Test
    void testConstructor() {
        GameRepository gameRepository = mock(GameRepository.class);
        WordService wordService = new WordService(mock(SpringWordRepository.class));
        RoundService roundService = new RoundService(mock(RoundRepository.class));
        assertNull((new GameService(gameRepository, wordService, roundService)).getMaxId());
        assertNull(roundService.getRoundMaxId());
    }

    @Test
    void testStartGame() {
        when(this.wordService.provideRandomWord((Integer) any())).thenReturn("Provide Random Word");

        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        ArrayList<TurnE> turnEList = new ArrayList<TurnE>();
        roundE.setTurns(turnEList);
        roundE.setGame(gameE);
        when(this.roundService.startRound((GameE) any(), (String) any())).thenReturn(roundE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);
        when(this.gameRepository.save((GameE) any())).thenReturn(gameE1);
        GameE actualStartGameResult = this.gameService.startGame();
        assertEquals(GameStatus.PAUSED, actualStartGameResult.getGamestatus());
        assertEquals(0, actualStartGameResult.getScore());
        assertEquals(turnEList, actualStartGameResult.getRound());
        verify(this.wordService).provideRandomWord((Integer) any());
        verify(this.roundService).startRound((GameE) any(), (String) any());
        verify(this.gameRepository).save((GameE) any());
        assertNull(this.gameService.getMaxId());
    }

    @BeforeEach
    void before() {
        gameE = new GameE(null, 0, GameStatus.PAUSED);
    }

    @Test
    void testGetGameById2() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);
        Optional<GameE> ofResult = Optional.<GameE>of(gameE);
        when(this.gameRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(gameE, this.gameService.getGameById(123L));
        verify(this.gameRepository).findById((Long) any());
        assertNull(this.gameService.getMaxId());
    }

    @Test
    void testGetMaxId() {
        when(this.gameRepository.maxId()).thenReturn(Optional.<Long>of(42L));
        assertEquals(42L, this.gameService.getMaxId().longValue());
        verify(this.gameRepository).maxId();
    }
}
