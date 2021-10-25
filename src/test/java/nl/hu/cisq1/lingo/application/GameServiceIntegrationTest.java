package nl.hu.cisq1.lingo.application;


import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import nl.hu.cisq1.lingo.CiTestConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Import(CiTestConfiguration.class)
public class GameServiceIntegrationTest {

    @Autowired
    private GameService service;

    @Test
    @DisplayName("A new round is started when a new game is started")
    void providesNewGame() {
        GameE gameE = service.startGame();
        assertEquals(0, gameE.getScore());
        assertEquals(GameStatus.PAUSED, gameE.getGamestatus());
        assertEquals(1, gameE.getRound().size());
    }
}
