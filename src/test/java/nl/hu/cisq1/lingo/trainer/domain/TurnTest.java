package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    @ParameterizedTest
    @MethodSource("provideTurnFeedbackExamples")
    public void giveTurnFeedback(Feedback feedback, Round round, int turnCount){
        Turn turn = new Turn(feedback, round, turnCount);
        System.out.println(turn.turnFeedback(new ArrayList<Mark>()));
    }

    private static Stream<Arguments> provideTurnFeedbackExamples(){
        return Stream.of(
                Arguments.of(
                        new Feedback("aalkorf", new ArrayList<Mark>()),
                        new Round(new Word("aardgas"), new Game(null, 0), null),
                        2));
    }
}