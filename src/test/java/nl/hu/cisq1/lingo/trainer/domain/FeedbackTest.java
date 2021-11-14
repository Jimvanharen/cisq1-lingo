package nl.hu.cisq1.lingo.trainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FeedbackTest {
    @Test
    void testConstructor() {
        Feedback actualFeedback = new Feedback();
        actualFeedback.setAttempt("Attempt");
        ArrayList<Mark> markList = new ArrayList<Mark>();
        actualFeedback.setMarks(markList);
        assertEquals("Attempt", actualFeedback.getAttempt());
        assertSame(markList, actualFeedback.getMarks());
        assertNull(actualFeedback.getTurnE());
    }

    @Test
    void testConstructor2() {
        ArrayList<Mark> markList = new ArrayList<Mark>();
        Turn turn = new Turn();
        Feedback actualFeedback = new Feedback(markList, turn, "Attempt");
        actualFeedback.setAttempt("Attempt");
        ArrayList<Mark> markList1 = new ArrayList<>();
        actualFeedback.setMarks(markList1);
        assertEquals("Attempt", actualFeedback.getAttempt());
        List<Mark> marks = actualFeedback.getMarks();
        assertSame(markList1, marks);
        assertEquals(markList, marks);
        assertSame(turn, actualFeedback.getTurnE());
    }

    @ParameterizedTest
    @MethodSource("provideFeedbackExamples")
    public void testGiveFeedback(String words, String attempt) {
        Round round = new Round(new Word(words), new Game(), new ArrayList<>());
        Turn turn = new Turn(attempt, 1, round);
        Feedback feedback = new Feedback(new ArrayList<>(), turn, attempt);
        List<Mark> marks = feedback.giveFeedback(new ArrayList<>(), round, turn);
        System.out.println(marks);

    }

    private static Stream<Arguments> provideFeedbackExamples() {
        return Stream.of(
                Arguments.of("woord", "woordt"),
                Arguments.of("aardgas", "aartgas"),
                Arguments.of("aardgas", "aardgas"),
                Arguments.of("adopter", "adoptie"));
    }
}
