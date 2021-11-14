package nl.hu.cisq1.lingo.trainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GameStatusTest {
    @Test
    void testValueOf() {
        assertEquals(GameStatus.ENDED, GameStatus.valueOf("ENDED"));
    }

    @Test
    void testValues() {
        GameStatus[] actualValuesResult = GameStatus.values();
        assertEquals(3, actualValuesResult.length);
        assertEquals(GameStatus.ENDED, actualValuesResult[0]);
        assertEquals(GameStatus.PAUSED, actualValuesResult[1]);
        assertEquals(GameStatus.PLAYING, actualValuesResult[2]);
    }
}

