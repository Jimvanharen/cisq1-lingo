package nl.hu.cisq1.lingo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.hu.cisq1.lingo.data.FeedbackE;
import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.HintE;
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
@ContextConfiguration(classes = {FeedbackService.class})
@ExtendWith(SpringExtension.class)
class FeedbackServiceTest {
    @MockBean
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackService feedbackService;

    @Test
    void testGiveFeedback() {
        ArrayList<Mark> prevFeedback = new ArrayList<Mark>();

        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word("Word"));
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);

        RoundE roundE1 = new RoundE();
        roundE1.setWord(new Word());
        roundE1.setTurns(new ArrayList<TurnE>());
        roundE1.setGame(gameE1);

        RoundE roundE2 = new RoundE();
        roundE2.setWord(new Word());
        roundE2.setTurns(new ArrayList<TurnE>());
        roundE2.setGame(new GameE());

        FeedbackE feedbackE = new FeedbackE();
        feedbackE.setMarks(new ArrayList<Mark>());
        feedbackE.setAttempt("Attempt");
        feedbackE.setTurnE(new TurnE());

        HintE hintE = new HintE();
        hintE.setHintList(new ArrayList<Character>());
        hintE.setTurnE(new TurnE());

        TurnE turnE = new TurnE();
        turnE.setRound(roundE2);
        turnE.setFeedback(feedbackE);
        turnE.setGuess("Guess");
        turnE.setHint(hintE);
        turnE.setTurnCount(3);

        FeedbackE feedbackE1 = new FeedbackE();
        feedbackE1.setMarks(new ArrayList<Mark>());
        feedbackE1.setAttempt("Attempt");
        feedbackE1.setTurnE(turnE);

        RoundE roundE3 = new RoundE();
        roundE3.setWord(new Word());
        roundE3.setTurns(new ArrayList<TurnE>());
        roundE3.setGame(new GameE());

        FeedbackE feedbackE2 = new FeedbackE();
        feedbackE2.setMarks(new ArrayList<Mark>());
        feedbackE2.setAttempt("Attempt");
        feedbackE2.setTurnE(new TurnE());

        HintE hintE1 = new HintE();
        hintE1.setHintList(new ArrayList<Character>());
        hintE1.setTurnE(new TurnE());

        TurnE turnE1 = new TurnE();
        turnE1.setRound(roundE3);
        turnE1.setFeedback(feedbackE2);
        turnE1.setGuess("Guess");
        turnE1.setHint(hintE1);
        turnE1.setTurnCount(3);

        HintE hintE2 = new HintE();
        hintE2.setHintList(new ArrayList<Character>());
        hintE2.setTurnE(turnE1);

        TurnE turnE2 = new TurnE();
        turnE2.setRound(roundE1);
        turnE2.setFeedback(feedbackE1);
        turnE2.setGuess("Guess");
        turnE2.setHint(hintE2);
        turnE2.setTurnCount(3);
        List<Mark> actualGiveFeedbackResult = this.feedbackService.giveFeedback(prevFeedback, roundE, turnE2);
        assertEquals(5, actualGiveFeedbackResult.size());
        assertEquals(Mark.INVALID, actualGiveFeedbackResult.get(0));
        assertEquals(Mark.INVALID, actualGiveFeedbackResult.get(1));
        assertEquals(Mark.INVALID, actualGiveFeedbackResult.get(2));
        assertEquals(Mark.INVALID, actualGiveFeedbackResult.get(3));
        assertEquals(Mark.INVALID, actualGiveFeedbackResult.get(4));
    }
}

