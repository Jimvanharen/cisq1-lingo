package nl.hu.cisq1.lingo.trainer.domain;

import static org.junit.jupiter.api.Assertions.*;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoundTest {

    private Round round;

    @BeforeEach
    private void initRound(){
        round = new Round(new Word("aardgas"), null, null);
    }

    @Test
    @DisplayName("Check round")
    private void checkRound(){
        assertEquals("aargas", round.getWord().getValue());
    }
}
