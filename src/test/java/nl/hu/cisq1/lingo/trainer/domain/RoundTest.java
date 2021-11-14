package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

@DisplayName("Testing the round domain")
public class RoundTest {

    private Round round;
    private Turn turn;

    @BeforeEach
    public void init(){
        turn = new Turn();
        round = new Round(null, null, new ArrayList<>());
    }

    @Test
    @DisplayName("Test if the assertion equals to true when adding a turn with a turncount below 5")
    public void testAddTurn(){
        assertTrue(round.addTurn(turn));
    }

    @Test
    @DisplayName("Test if the assertion equals false when adding a a turn with a turncount over 5")
    public void testAddTurnBranches(){
        turn.setTurnCount(6);
        assertFalse(round.addTurn(turn));
    }

    @Test
    @DisplayName("Test if round could start correctly")
    public void testStartRound(){
        Game game = new Game(new ArrayList<>(), 0, GameStatus.PAUSED);
        round.startRound(game, "");
        assertEquals(game.getRounds().size(), 1);
    }
}
