package nl.hu.cisq1.lingo.trainer.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    private void setGame(){
        game = new Game(new Round(new Word("aardgas"), null, new Turn(null, null, 0)), 10);
    }

    @Test
    @DisplayName("Check if attributes work correctly")
    public void checkGameAttr(){
        assertEquals("aardgas", game.getRound().getWord().getToBeGuessedWord());
        assertEquals(0, game.getRound().getTurn().getTurnCount());
        assertEquals(10, game.getScore());
    }
}