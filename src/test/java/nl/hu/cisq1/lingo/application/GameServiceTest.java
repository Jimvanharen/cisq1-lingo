package nl.hu.cisq1.lingo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.HintRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ContextConfiguration(classes = {GameService.class, RoundService.class, WordService.class})
@ExtendWith(SpringExtension.class)
class GameServiceTest {
    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @MockBean
    private RoundService roundService;

    @MockBean
    private WordService wordService;

    @Test
    void testStartGame() {
        when(this.wordService.provideRandomWord((Integer) any())).thenReturn("Provide Random Word");

        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        ArrayList<TurnE> turnEList = new ArrayList<>();
        roundE.setTurns(turnEList);
        roundE.setGame(gameE);
        when(this.roundService.startRound((GameE) any(), (String) any())).thenReturn(roundE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<>());
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
}

