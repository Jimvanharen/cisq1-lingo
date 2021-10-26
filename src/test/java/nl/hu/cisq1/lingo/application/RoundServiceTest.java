package nl.hu.cisq1.lingo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoundServiceTest {

    @MockBean
    private RoundRepository roundRepository;

    @Autowired
    private RoundService roundService;

    @Test
    void testConstructor() {
        assertNull((new RoundService(mock(RoundRepository.class))).getRoundMaxId());
    }

    @Test
    void testStartRound() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        ArrayList<TurnE> turnEList = new ArrayList<TurnE>();
        roundE.setTurns(turnEList);
        roundE.setGame(gameE);
        when(this.roundRepository.save((RoundE) any())).thenReturn(roundE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);
        RoundE actualStartRoundResult = this.roundService.startRound(gameE1, "To Be Guessed Word");
        GameE game = actualStartRoundResult.getGame();
        assertSame(gameE1, game);
        assertEquals(turnEList, actualStartRoundResult.getTurns());
        Word word = actualStartRoundResult.getWord();
        assertEquals("To Be Guessed Word", word.getValue());
        assertEquals(18, word.getLength().intValue());
        assertEquals(1, game.getRound().size());
        verify(this.roundRepository).save((RoundE) any());
        assertNull(this.roundService.getRoundMaxId());
    }

    @Test
    void testRemoveRound() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);
        Optional<RoundE> ofResult = Optional.<RoundE>of(roundE);
        doNothing().when(this.roundRepository).delete((RoundE) any());
        when(this.roundRepository.findById((Long) any())).thenReturn(ofResult);
        this.roundService.removeRound(123L);
        verify(this.roundRepository).delete((RoundE) any());
        verify(this.roundRepository).findById((Long) any());
        assertNull(this.roundService.getRoundMaxId());
    }

    @Test
    void testRemoveRound2() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);
        Optional<RoundE> ofResult = Optional.<RoundE>of(roundE);
        doNothing().when(this.roundRepository).delete((RoundE) any());
        when(this.roundRepository.findById((Long) any())).thenReturn(ofResult);
        this.roundService.removeRound(0L);
        verify(this.roundRepository).delete((RoundE) any());
        verify(this.roundRepository).findById((Long) any());
        assertNull(this.roundService.getRoundMaxId());
    }

    @Test
    void testGetRoundMaxId() {
        when(this.roundRepository.maxId()).thenReturn(Optional.<Long>of(42L));
        assertEquals(42L, this.roundService.getRoundMaxId().longValue());
        verify(this.roundRepository).maxId();
    }

    @Test
    void testGetRoundById() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);
        Optional<RoundE> ofResult = Optional.<RoundE>of(roundE);
        when(this.roundRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(roundE, this.roundService.getRoundById(123L));
        verify(this.roundRepository).findById((Long) any());
        assertNull(this.roundService.getRoundMaxId());
    }


}
