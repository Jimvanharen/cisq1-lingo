package nl.hu.cisq1.lingo.trainer.domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HintTest {

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Gives hint according to the to be guessed word")
    public void giveHint(List<Character> previousHint, String wordToGuess, List<Mark> marks){
        Hint hint = new Hint(previousHint, new Feedback(wordToGuess, marks));
        Hint nextHint = hint.giveHint();
        assertNotEquals(nextHint.getHintList(), previousHint);
        assertEquals(List.of('a', 'a', 'r', 'd', 'g', 'a', 's'), nextHint.getHintList());
    }

    private static Stream<Arguments> provideHintExamples(){
        return Stream.of(
                Arguments.of(
                        List.of('a', 'a', '.', '.', '.', 'a', '.'),
                        "aardgas",
                        List.of(
                        Mark.CORRECT, Mark.CORRECT,
                        Mark.CORRECT, Mark.CORRECT,
                        Mark.CORRECT, Mark.CORRECT,
                        Mark.CORRECT
                )));
    }
}