package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.TurnCountException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    @Test
    void testConstructor() {
        Turn actualTurn = new Turn();
        assertNull(actualTurn.getFeedback());
        assertNull(actualTurn.getGuess());
        assertNull(actualTurn.getHint());
        assertNull(actualTurn.getRound());
        assertEquals(0, actualTurn.getTurnCount());
    }

    @Test
    void testConstructor2() {
        Round round = new Round();
        Turn actualTurn = new Turn("Guess", 3, round);

        assertNull(actualTurn.getFeedback());
        assertEquals("Guess", actualTurn.getGuess());
        assertNull(actualTurn.getHint());
        assertSame(round, actualTurn.getRound());
        assertEquals(3, actualTurn.getTurnCount());
    }

    @Test
    void testConstructor3() {
        Feedback feedback = new Feedback();
        Hint hint = new Hint();
        Round round = new Round();
        Turn actualTurn = new Turn(feedback, hint, round);

        assertSame(feedback, actualTurn.getFeedback());
        assertNull(actualTurn.getGuess());
        assertSame(hint, actualTurn.getHint());
        assertSame(round, actualTurn.getRound());
        assertEquals(0, actualTurn.getTurnCount());
    }

    @Test
    void testConstructor4() {
        Feedback feedback = new Feedback();
        Round round = new Round();
        Hint hint = new Hint();
        Turn actualTurn = new Turn(feedback, round, "Guess", 3, hint);

        assertSame(feedback, actualTurn.getFeedback());
        assertEquals("Guess", actualTurn.getGuess());
        assertSame(hint, actualTurn.getHint());
        assertSame(round, actualTurn.getRound());
        assertEquals(3, actualTurn.getTurnCount());
    }

    @Test
    void testConstructor5() {
        Round round = new Round();
        Turn actualTurn = new Turn(round);
        assertNull(actualTurn.getFeedback());
        assertNull(actualTurn.getGuess());
        assertNull(actualTurn.getHint());
        assertSame(round, actualTurn.getRound());
        assertEquals(0, actualTurn.getTurnCount());
    }

    @Test
    @DisplayName("Test if the turn gets an out of bounds turn exception")
    void testStartTurn(){
        Round round = new Round(new Word("foo"), null, new ArrayList<>());
        for(int i =0; i < 10; i++){
            round.addTurn(new Turn());
        }
        assertThrows(TurnCountException.class, () ->{
            round.getTurns().get(0).startTurn(round, "");

        });
    }
}

