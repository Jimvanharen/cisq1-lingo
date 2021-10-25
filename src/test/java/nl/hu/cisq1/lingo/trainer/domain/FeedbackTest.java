package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    Feedback feedback;

    @BeforeEach
    public void beforeAll(){
        feedback = new Feedback("aardgas", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
                Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
    }

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    public void wordIsGuessed() {
        Feedback fb = new Feedback("woord",
                List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(fb.isWordGuessed(fb));
    }

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    public void wordIsNotGuessed() {
        Feedback fb = new Feedback("woordt",
                List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT));
        assertFalse(fb.isWordGuessed(fb));
    }

    @Test
    @DisplayName("Test attributes")
    public void testAttr(){
        assertEquals("aardgas", feedback.getAttempt());
        assertEquals(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT,
                Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), feedback.getMark());
    }
}