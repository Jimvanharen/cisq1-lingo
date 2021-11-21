package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class HintTest {
    @Test
    void testConstructor() {
        Hint actualHint = new Hint();
        ArrayList<Character> characterList = new ArrayList<Character>();
        actualHint.setHintList(characterList);
        assertSame(characterList, actualHint.getHintList());
        assertNull(actualHint.getTurnE());
    }

    @Test
    void testConstructor2() {
        ArrayList<Character> characterList = new ArrayList<Character>();
        Turn turn = new Turn();
        Hint actualHint = new Hint(characterList, turn);
        ArrayList<Character> characterList1 = new ArrayList<Character>();
        actualHint.setHintList(characterList1);
        List<Character> hintList = actualHint.getHintList();
        assertSame(characterList1, hintList);
        assertEquals(characterList, hintList);
        assertSame(turn, actualHint.getTurnE());
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Gives hint according to the to be guessed word")
    public void giveHint(List<Character> previousHint, String wordToGuess, List<Mark> marks){
        Hint hint = new Hint();
        Hint nextHint = hint.giveHint(new Turn(new Feedback(marks, new Turn(), wordToGuess), hint, new Round()),
                previousHint);

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
    @Test
    @DisplayName("Test nullpointer exception")
    void testNullpointer(){
        Hint hint = new Hint();
        assertThrows(NullPointerException.class, () -> hint.giveHint(new Turn(null, hint, new Round()),
               null));
    }


}

