package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(CiTestConfiguration.class)
public class TrainerServiceIntegrationTest {

    @Autowired
    TrainerService trainerService;

    @Test
    @DisplayName("Testing if game gets started")
    void testStartGame(){
        Game game = trainerService.startGame();
        assertEquals(game.getGamestatus(), GameStatus.PLAYING);
        assertTrue(game.getRounds().size() > 1);
        assertEquals(game.getScore(), 0 );
    }
}
