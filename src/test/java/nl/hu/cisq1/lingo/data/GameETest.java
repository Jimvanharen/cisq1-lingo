package nl.hu.cisq1.lingo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.words.domain.Word;

import org.junit.jupiter.api.Test;

class GameETest {
    @Test
    void testConstructor() {
        GameE actualGameE = new GameE();
        ArrayList<RoundE> roundEList = new ArrayList<RoundE>();
        actualGameE.setRound(roundEList);
        actualGameE.setScore(3);
        assertNull(actualGameE.getGamestatus());
        assertSame(roundEList, actualGameE.getRound());
        assertEquals(3, actualGameE.getScore());
    }

    @Test
    void testConstructor2() {
        ArrayList<RoundE> roundEList = new ArrayList<RoundE>();
        GameE actualGameE = new GameE(roundEList, 3, GameStatus.ENDED);
        ArrayList<RoundE> roundEList1 = new ArrayList<RoundE>();
        actualGameE.setRound(roundEList1);
        actualGameE.setScore(3);
        assertEquals(GameStatus.ENDED, actualGameE.getGamestatus());
        List<RoundE> round = actualGameE.getRound();
        assertSame(roundEList1, round);
        assertEquals(roundEList, round);
        assertEquals(3, actualGameE.getScore());
    }

    @Test
    void testAddRound() {
        GameE gameE = new GameE(new ArrayList<RoundE>(), 3, GameStatus.ENDED);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE1);
        gameE.addRound(roundE);
        assertEquals(1, gameE.getRound().size());
    }
}

