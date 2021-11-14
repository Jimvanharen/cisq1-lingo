package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

@DisplayName("Testing the game domain")
public class GameTest {

    public Game game;

    @BeforeEach
    public void init(){
        game = new Game(new ArrayList<>(), 0, GameStatus.PAUSED);
    }

    @Test
    @DisplayName("Testing if round was added succesfully")
    public void testAddRound(){
        assertTrue(game.addRound(new Round()));
    }

    @Test
    @DisplayName("Testing if score was added succesfully")
    public void testScore(){
        assertEquals(game.getScore(), 0L);
    }

    @Test
    @DisplayName("Testing if gamestatus was added succesfully")
    public void testGameStatus(){
        assertEquals(game.getGamestatus(), GameStatus.PAUSED);
    }
}
