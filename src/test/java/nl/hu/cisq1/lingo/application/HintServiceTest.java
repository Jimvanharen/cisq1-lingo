package nl.hu.cisq1.lingo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.hu.cisq1.lingo.data.FeedbackE;
import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.HintE;
import nl.hu.cisq1.lingo.data.HintRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ContextConfiguration(classes = {HintService.class})
@ExtendWith(SpringExtension.class)
class HintServiceTest {
    @MockBean
    private HintRepository hintRepository;

    @Autowired
    private HintService hintService;

    @Test
    void testGiveHint() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word("Word"));
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);

        RoundE roundE1 = new RoundE();
        roundE1.setWord(new Word());
        roundE1.setTurns(new ArrayList<TurnE>());
        roundE1.setGame(new GameE());

        FeedbackE feedbackE = new FeedbackE();
        feedbackE.setMarks(new ArrayList<Mark>());
        feedbackE.setAttempt("Attempt");
        feedbackE.setTurnE(new TurnE());

        HintE hintE = new HintE();
        hintE.setHintList(new ArrayList<Character>());
        hintE.setTurnE(new TurnE());

        TurnE turnE = new TurnE();
        turnE.setRound(roundE1);
        turnE.setFeedback(feedbackE);
        turnE.setGuess("Guess");
        turnE.setHint(hintE);
        turnE.setTurnCount(3);

        FeedbackE feedbackE1 = new FeedbackE();
        feedbackE1.setMarks(new ArrayList<Mark>());
        feedbackE1.setAttempt("Attempt");
        feedbackE1.setTurnE(turnE);

        RoundE roundE2 = new RoundE();
        roundE2.setWord(new Word());
        roundE2.setTurns(new ArrayList<TurnE>());
        roundE2.setGame(new GameE());

        FeedbackE feedbackE2 = new FeedbackE();
        feedbackE2.setMarks(new ArrayList<Mark>());
        feedbackE2.setAttempt("Attempt");
        feedbackE2.setTurnE(new TurnE());

        HintE hintE1 = new HintE();
        hintE1.setHintList(new ArrayList<Character>());
        hintE1.setTurnE(new TurnE());

        TurnE turnE1 = new TurnE();
        turnE1.setRound(roundE2);
        turnE1.setFeedback(feedbackE2);
        turnE1.setGuess("Guess");
        turnE1.setHint(hintE1);
        turnE1.setTurnCount(3);

        HintE hintE2 = new HintE();
        hintE2.setHintList(new ArrayList<Character>());
        hintE2.setTurnE(turnE1);

        TurnE turnE2 = new TurnE();
        turnE2.setRound(roundE);
        turnE2.setFeedback(feedbackE1);
        turnE2.setGuess("Guess");
        turnE2.setHint(hintE2);
        turnE2.setTurnCount(3);
        HintE actualGiveHintResult = this.hintService.giveHint(turnE2, new ArrayList<Character>());
        List<Character> hintList = actualGiveHintResult.getHintList();
        assertEquals(4, hintList.size());
        assertEquals('W', hintList.get(0));
        assertEquals('.', hintList.get(1));
        assertEquals('.', hintList.get(2));
        assertEquals('.', hintList.get(3));
        assertSame(turnE2, actualGiveHintResult.getTurnE());
    }

    @Test
    void testProvideFirstLetter() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word("Word"));
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);

        RoundE roundE1 = new RoundE();
        roundE1.setWord(new Word());
        roundE1.setTurns(new ArrayList<TurnE>());
        roundE1.setGame(new GameE());

        FeedbackE feedbackE = new FeedbackE();
        feedbackE.setMarks(new ArrayList<Mark>());
        feedbackE.setAttempt("Attempt");
        feedbackE.setTurnE(new TurnE());

        HintE hintE = new HintE();
        hintE.setHintList(new ArrayList<Character>());
        hintE.setTurnE(new TurnE());

        TurnE turnE = new TurnE();
        turnE.setRound(roundE1);
        turnE.setFeedback(feedbackE);
        turnE.setGuess("Guess");
        turnE.setHint(hintE);
        turnE.setTurnCount(3);

        FeedbackE feedbackE1 = new FeedbackE();
        feedbackE1.setMarks(new ArrayList<Mark>());
        feedbackE1.setAttempt("Attempt");
        feedbackE1.setTurnE(turnE);

        RoundE roundE2 = new RoundE();
        roundE2.setWord(new Word());
        roundE2.setTurns(new ArrayList<TurnE>());
        roundE2.setGame(new GameE());

        FeedbackE feedbackE2 = new FeedbackE();
        feedbackE2.setMarks(new ArrayList<Mark>());
        feedbackE2.setAttempt("Attempt");
        feedbackE2.setTurnE(new TurnE());

        HintE hintE1 = new HintE();
        hintE1.setHintList(new ArrayList<Character>());
        hintE1.setTurnE(new TurnE());

        TurnE turnE1 = new TurnE();
        turnE1.setRound(roundE2);
        turnE1.setFeedback(feedbackE2);
        turnE1.setGuess("Guess");
        turnE1.setHint(hintE1);
        turnE1.setTurnCount(3);

        HintE hintE2 = new HintE();
        hintE2.setHintList(new ArrayList<Character>());
        hintE2.setTurnE(turnE1);

        TurnE turnE2 = new TurnE();
        turnE2.setRound(roundE);
        turnE2.setFeedback(feedbackE1);
        turnE2.setGuess("Guess");
        turnE2.setHint(hintE2);
        turnE2.setTurnCount(3);
        HintE actualProvideFirstLetterResult = this.hintService.provideFirstLetter(turnE2);
        List<Character> hintList = actualProvideFirstLetterResult.getHintList();
        assertEquals(4, hintList.size());
        assertEquals('W', hintList.get(0));
        assertEquals('.', hintList.get(1));
        assertEquals('.', hintList.get(2));
        assertEquals('.', hintList.get(3));
        assertSame(turnE2, actualProvideFirstLetterResult.getTurnE());
    }
}

