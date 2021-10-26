package nl.hu.cisq1.lingo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.hu.cisq1.lingo.data.FeedbackE;
import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.HintE;
import nl.hu.cisq1.lingo.data.HintRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.Turn;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TurnServiceTest {

    @MockBean
    private FeedbackService feedbackService;

    @MockBean
    private HintService hintService;

    @MockBean
    private TurnRepository turnRepository;

    @Autowired
    private TurnService turnService;

    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TurnService.turnRepository
        //     TurnService.hintService
        //     TurnService.feedbackService

        TurnRepository turnRepository = mock(TurnRepository.class);
        HintRepository hintRepository = mock(HintRepository.class);
        HintService hintService = new HintService(hintRepository, new RoundService(mock(RoundRepository.class)));

        new TurnService(turnRepository, hintService, new FeedbackService(mock(FeedbackRepository.class)));

    }

    @Test
    void testConstructor2() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TurnService.turnRepository
        //     TurnService.hintService
        //     TurnService.feedbackService

        TurnRepository turnRepository = mock(TurnRepository.class);
        HintRepository hintRepository = mock(HintRepository.class);
        HintService hintService = new HintService(hintRepository, new RoundService(mock(RoundRepository.class)));

        new TurnService(turnRepository, hintService, new FeedbackService(mock(FeedbackRepository.class)));

    }

    @Test
    void testConstructor3() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TurnService.turnRepository
        //     TurnService.hintService
        //     TurnService.feedbackService

        TurnRepository turnRepository = mock(TurnRepository.class);
        HintRepository hintRepository = mock(HintRepository.class);
        HintService hintService = new HintService(hintRepository, new RoundService(mock(RoundRepository.class)));

        new TurnService(turnRepository, hintService, new FeedbackService(mock(FeedbackRepository.class)));

    }

    @Test
    void testStartTurn() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);

        RoundE roundE1 = new RoundE();
        roundE1.setWord(new Word());
        roundE1.setTurns(new ArrayList<TurnE>());
        roundE1.setGame(gameE1);

        TurnE turnE = new TurnE();
        turnE.setRound(new RoundE());
        turnE.setFeedback(new FeedbackE());
        turnE.setGuess("Guess");
        turnE.setHint(new HintE());
        turnE.setTurnCount(3);

        FeedbackE feedbackE = new FeedbackE();
        feedbackE.setMarks(new ArrayList<Mark>());
        feedbackE.setAttempt("Attempt");
        feedbackE.setTurnE(turnE);

        TurnE turnE1 = new TurnE();
        turnE1.setRound(new RoundE());
        turnE1.setFeedback(new FeedbackE());
        turnE1.setGuess("Guess");
        turnE1.setHint(new HintE());
        turnE1.setTurnCount(3);

        HintE hintE = new HintE();
        hintE.setHintList(new ArrayList<Character>());
        hintE.setTurnE(turnE1);

        TurnE turnE2 = new TurnE();
        turnE2.setRound(roundE1);
        turnE2.setFeedback(feedbackE);
        turnE2.setGuess("Guess");
        turnE2.setHint(hintE);
        turnE2.setTurnCount(3);

        FeedbackE feedbackE1 = new FeedbackE();
        feedbackE1.setMarks(new ArrayList<Mark>());
        feedbackE1.setAttempt("Attempt");
        feedbackE1.setTurnE(turnE2);

        GameE gameE2 = new GameE();
        gameE2.setRound(new ArrayList<RoundE>());
        gameE2.setScore(3);

        RoundE roundE2 = new RoundE();
        roundE2.setWord(new Word());
        roundE2.setTurns(new ArrayList<TurnE>());
        roundE2.setGame(gameE2);

        TurnE turnE3 = new TurnE();
        turnE3.setRound(new RoundE());
        turnE3.setFeedback(new FeedbackE());
        turnE3.setGuess("Guess");
        turnE3.setHint(new HintE());
        turnE3.setTurnCount(3);

        FeedbackE feedbackE2 = new FeedbackE();
        feedbackE2.setMarks(new ArrayList<Mark>());
        feedbackE2.setAttempt("Attempt");
        feedbackE2.setTurnE(turnE3);

        TurnE turnE4 = new TurnE();
        turnE4.setRound(new RoundE());
        turnE4.setFeedback(new FeedbackE());
        turnE4.setGuess("Guess");
        turnE4.setHint(new HintE());
        turnE4.setTurnCount(3);

        HintE hintE1 = new HintE();
        hintE1.setHintList(new ArrayList<Character>());
        hintE1.setTurnE(turnE4);

        TurnE turnE5 = new TurnE();
        turnE5.setRound(roundE2);
        turnE5.setFeedback(feedbackE2);
        turnE5.setGuess("Guess");
        turnE5.setHint(hintE1);
        turnE5.setTurnCount(3);

        HintE hintE2 = new HintE();
        hintE2.setHintList(new ArrayList<Character>());
        hintE2.setTurnE(turnE5);

        TurnE turnE6 = new TurnE();
        turnE6.setRound(roundE);
        turnE6.setFeedback(feedbackE1);
        turnE6.setGuess("Guess");
        turnE6.setHint(hintE2);
        turnE6.setTurnCount(3);
        when(this.turnRepository.save((TurnE) any())).thenReturn(turnE6);

        GameE gameE3 = new GameE();
        gameE3.setRound(new ArrayList<RoundE>());
        gameE3.setScore(3);

        RoundE roundE3 = new RoundE();
        roundE3.setWord(new Word());
        roundE3.setTurns(new ArrayList<TurnE>());
        roundE3.setGame(gameE3);

        RoundE roundE4 = new RoundE();
        roundE4.setWord(new Word());
        roundE4.setTurns(new ArrayList<TurnE>());
        roundE4.setGame(new GameE());

        FeedbackE feedbackE3 = new FeedbackE();
        feedbackE3.setMarks(new ArrayList<Mark>());
        feedbackE3.setAttempt("Attempt");
        feedbackE3.setTurnE(new TurnE());

        HintE hintE3 = new HintE();
        hintE3.setHintList(new ArrayList<Character>());
        hintE3.setTurnE(new TurnE());

        TurnE turnE7 = new TurnE();
        turnE7.setRound(roundE4);
        turnE7.setFeedback(feedbackE3);
        turnE7.setGuess("Guess");
        turnE7.setHint(hintE3);
        turnE7.setTurnCount(3);

        FeedbackE feedbackE4 = new FeedbackE();
        feedbackE4.setMarks(new ArrayList<Mark>());
        feedbackE4.setAttempt("Attempt");
        feedbackE4.setTurnE(turnE7);

        RoundE roundE5 = new RoundE();
        roundE5.setWord(new Word());
        roundE5.setTurns(new ArrayList<TurnE>());
        roundE5.setGame(new GameE());

        FeedbackE feedbackE5 = new FeedbackE();
        feedbackE5.setMarks(new ArrayList<Mark>());
        feedbackE5.setAttempt("Attempt");
        feedbackE5.setTurnE(new TurnE());

        HintE hintE4 = new HintE();
        hintE4.setHintList(new ArrayList<Character>());
        hintE4.setTurnE(new TurnE());

        TurnE turnE8 = new TurnE();
        turnE8.setRound(roundE5);
        turnE8.setFeedback(feedbackE5);
        turnE8.setGuess("Guess");
        turnE8.setHint(hintE4);
        turnE8.setTurnCount(3);

        HintE hintE5 = new HintE();
        hintE5.setHintList(new ArrayList<Character>());
        hintE5.setTurnE(turnE8);

        TurnE turnE9 = new TurnE();
        turnE9.setRound(roundE3);
        turnE9.setFeedback(feedbackE4);
        turnE9.setGuess("Guess");
        turnE9.setHint(hintE5);
        turnE9.setTurnCount(3);

        HintE hintE6 = new HintE();
        hintE6.setHintList(new ArrayList<Character>());
        hintE6.setTurnE(turnE9);
        when(this.hintService.giveHint((TurnE) any(), (List<Character>) any())).thenReturn(hintE6);
        when(this.feedbackService.giveFeedback((List<Mark>) any(), (RoundE) any(), (TurnE) any()))
                .thenReturn(new ArrayList<Mark>());

        GameE gameE4 = new GameE();
        ArrayList<RoundE> roundEList = new ArrayList<RoundE>();
        gameE4.setRound(roundEList);
        gameE4.setScore(3);

        RoundE roundE6 = new RoundE();
        roundE6.setWord(new Word());
        roundE6.setTurns(new ArrayList<TurnE>());
        roundE6.setGame(gameE4);
        TurnE actualStartTurnResult = this.turnService.startTurn(roundE6, "Guess");
        assertEquals(1, actualStartTurnResult.getTurnCount());
        assertEquals("Guess", actualStartTurnResult.getGuess());
        RoundE round = actualStartTurnResult.getRound();
        assertSame(roundE6, round);
        assertSame(hintE6, actualStartTurnResult.getHint());
        FeedbackE feedback = actualStartTurnResult.getFeedback();
        assertEquals(roundEList, feedback.getMarks());
        assertSame(actualStartTurnResult, feedback.getTurnE());
        assertEquals("Guess", feedback.getAttempt());
        assertEquals(1, round.getTurns().size());
        verify(this.turnRepository).save((TurnE) any());
        verify(this.hintService).giveHint((TurnE) any(), (List<Character>) any());
        verify(this.feedbackService).giveFeedback((List<Mark>) any(), (RoundE) any(), (TurnE) any());
    }

    @Test
    void testStartTurn2() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);

        RoundE roundE1 = new RoundE();
        roundE1.setWord(new Word());
        roundE1.setTurns(new ArrayList<TurnE>());
        roundE1.setGame(gameE1);

        TurnE turnE = new TurnE();
        turnE.setRound(new RoundE());
        turnE.setFeedback(new FeedbackE());
        turnE.setGuess("Guess");
        turnE.setHint(new HintE());
        turnE.setTurnCount(3);

        FeedbackE feedbackE = new FeedbackE();
        feedbackE.setMarks(new ArrayList<Mark>());
        feedbackE.setAttempt("Attempt");
        feedbackE.setTurnE(turnE);

        TurnE turnE1 = new TurnE();
        turnE1.setRound(new RoundE());
        turnE1.setFeedback(new FeedbackE());
        turnE1.setGuess("Guess");
        turnE1.setHint(new HintE());
        turnE1.setTurnCount(3);

        HintE hintE = new HintE();
        hintE.setHintList(new ArrayList<Character>());
        hintE.setTurnE(turnE1);

        TurnE turnE2 = new TurnE();
        turnE2.setRound(roundE1);
        turnE2.setFeedback(feedbackE);
        turnE2.setGuess("Guess");
        turnE2.setHint(hintE);
        turnE2.setTurnCount(3);

        FeedbackE feedbackE1 = new FeedbackE();
        feedbackE1.setMarks(new ArrayList<Mark>());
        feedbackE1.setAttempt("Attempt");
        feedbackE1.setTurnE(turnE2);

        GameE gameE2 = new GameE();
        gameE2.setRound(new ArrayList<RoundE>());
        gameE2.setScore(3);

        RoundE roundE2 = new RoundE();
        roundE2.setWord(new Word());
        roundE2.setTurns(new ArrayList<TurnE>());
        roundE2.setGame(gameE2);

        TurnE turnE3 = new TurnE();
        turnE3.setRound(new RoundE());
        turnE3.setFeedback(new FeedbackE());
        turnE3.setGuess("Guess");
        turnE3.setHint(new HintE());
        turnE3.setTurnCount(3);

        FeedbackE feedbackE2 = new FeedbackE();
        feedbackE2.setMarks(new ArrayList<Mark>());
        feedbackE2.setAttempt("Attempt");
        feedbackE2.setTurnE(turnE3);

        TurnE turnE4 = new TurnE();
        turnE4.setRound(new RoundE());
        turnE4.setFeedback(new FeedbackE());
        turnE4.setGuess("Guess");
        turnE4.setHint(new HintE());
        turnE4.setTurnCount(3);

        HintE hintE1 = new HintE();
        hintE1.setHintList(new ArrayList<Character>());
        hintE1.setTurnE(turnE4);

        TurnE turnE5 = new TurnE();
        turnE5.setRound(roundE2);
        turnE5.setFeedback(feedbackE2);
        turnE5.setGuess("Guess");
        turnE5.setHint(hintE1);
        turnE5.setTurnCount(3);

        HintE hintE2 = new HintE();
        hintE2.setHintList(new ArrayList<Character>());
        hintE2.setTurnE(turnE5);

        TurnE turnE6 = new TurnE();
        turnE6.setRound(roundE);
        turnE6.setFeedback(feedbackE1);
        turnE6.setGuess("Guess");
        turnE6.setHint(hintE2);
        turnE6.setTurnCount(3);
        when(this.turnRepository.save((TurnE) any())).thenReturn(turnE6);

        GameE gameE3 = new GameE();
        gameE3.setRound(new ArrayList<RoundE>());
        gameE3.setScore(3);

        RoundE roundE3 = new RoundE();
        roundE3.setWord(new Word());
        roundE3.setTurns(new ArrayList<TurnE>());
        roundE3.setGame(gameE3);

        RoundE roundE4 = new RoundE();
        roundE4.setWord(new Word());
        roundE4.setTurns(new ArrayList<TurnE>());
        roundE4.setGame(new GameE());

        FeedbackE feedbackE3 = new FeedbackE();
        feedbackE3.setMarks(new ArrayList<Mark>());
        feedbackE3.setAttempt("Attempt");
        feedbackE3.setTurnE(new TurnE());

        HintE hintE3 = new HintE();
        hintE3.setHintList(new ArrayList<Character>());
        hintE3.setTurnE(new TurnE());

        TurnE turnE7 = new TurnE();
        turnE7.setRound(roundE4);
        turnE7.setFeedback(feedbackE3);
        turnE7.setGuess("Guess");
        turnE7.setHint(hintE3);
        turnE7.setTurnCount(3);

        FeedbackE feedbackE4 = new FeedbackE();
        feedbackE4.setMarks(new ArrayList<Mark>());
        feedbackE4.setAttempt("Attempt");
        feedbackE4.setTurnE(turnE7);

        RoundE roundE5 = new RoundE();
        roundE5.setWord(new Word());
        roundE5.setTurns(new ArrayList<TurnE>());
        roundE5.setGame(new GameE());

        FeedbackE feedbackE5 = new FeedbackE();
        feedbackE5.setMarks(new ArrayList<Mark>());
        feedbackE5.setAttempt("Attempt");
        feedbackE5.setTurnE(new TurnE());

        HintE hintE4 = new HintE();
        hintE4.setHintList(new ArrayList<Character>());
        hintE4.setTurnE(new TurnE());

        TurnE turnE8 = new TurnE();
        turnE8.setRound(roundE5);
        turnE8.setFeedback(feedbackE5);
        turnE8.setGuess("Guess");
        turnE8.setHint(hintE4);
        turnE8.setTurnCount(3);

        HintE hintE5 = new HintE();
        hintE5.setHintList(new ArrayList<Character>());
        hintE5.setTurnE(turnE8);

        TurnE turnE9 = new TurnE();
        turnE9.setRound(roundE3);
        turnE9.setFeedback(feedbackE4);
        turnE9.setGuess("Guess");
        turnE9.setHint(hintE5);
        turnE9.setTurnCount(3);

        HintE hintE6 = new HintE();
        hintE6.setHintList(new ArrayList<Character>());
        hintE6.setTurnE(turnE9);
        when(this.hintService.giveHint((TurnE) any(), (List<Character>) any())).thenReturn(hintE6);
        when(this.feedbackService.giveFeedback((List<Mark>) any(), (RoundE) any(), (TurnE) any()))
                .thenReturn(new ArrayList<Mark>());

        GameE gameE4 = new GameE();
        ArrayList<RoundE> roundEList = new ArrayList<RoundE>();
        gameE4.setRound(roundEList);
        gameE4.setScore(3);

        RoundE roundE6 = new RoundE();
        roundE6.setWord(new Word());
        roundE6.setTurns(new ArrayList<TurnE>());
        roundE6.setGame(gameE4);
        TurnE actualStartTurnResult = this.turnService.startTurn(roundE6, "Guess");
        assertEquals(1, actualStartTurnResult.getTurnCount());
        assertEquals("Guess", actualStartTurnResult.getGuess());
        RoundE round = actualStartTurnResult.getRound();
        assertSame(roundE6, round);
        assertSame(hintE6, actualStartTurnResult.getHint());
        FeedbackE feedback = actualStartTurnResult.getFeedback();
        assertEquals(roundEList, feedback.getMarks());
        assertSame(actualStartTurnResult, feedback.getTurnE());
        assertEquals("Guess", feedback.getAttempt());
        assertEquals(1, round.getTurns().size());
        verify(this.turnRepository).save((TurnE) any());
        verify(this.hintService).giveHint((TurnE) any(), (List<Character>) any());
        verify(this.feedbackService).giveFeedback((List<Mark>) any(), (RoundE) any(), (TurnE) any());
    }

    @Test
    void testStartTurn3() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);

        RoundE roundE1 = new RoundE();
        roundE1.setWord(new Word());
        roundE1.setTurns(new ArrayList<TurnE>());
        roundE1.setGame(gameE1);

        TurnE turnE = new TurnE();
        turnE.setRound(new RoundE());
        turnE.setFeedback(new FeedbackE());
        turnE.setGuess("Guess");
        turnE.setHint(new HintE());
        turnE.setTurnCount(3);

        FeedbackE feedbackE = new FeedbackE();
        feedbackE.setMarks(new ArrayList<Mark>());
        feedbackE.setAttempt("Attempt");
        feedbackE.setTurnE(turnE);

        TurnE turnE1 = new TurnE();
        turnE1.setRound(new RoundE());
        turnE1.setFeedback(new FeedbackE());
        turnE1.setGuess("Guess");
        turnE1.setHint(new HintE());
        turnE1.setTurnCount(3);

        HintE hintE = new HintE();
        hintE.setHintList(new ArrayList<Character>());
        hintE.setTurnE(turnE1);

        TurnE turnE2 = new TurnE();
        turnE2.setRound(roundE1);
        turnE2.setFeedback(feedbackE);
        turnE2.setGuess("Guess");
        turnE2.setHint(hintE);
        turnE2.setTurnCount(3);

        FeedbackE feedbackE1 = new FeedbackE();
        feedbackE1.setMarks(new ArrayList<Mark>());
        feedbackE1.setAttempt("Attempt");
        feedbackE1.setTurnE(turnE2);

        GameE gameE2 = new GameE();
        gameE2.setRound(new ArrayList<RoundE>());
        gameE2.setScore(3);

        RoundE roundE2 = new RoundE();
        roundE2.setWord(new Word());
        roundE2.setTurns(new ArrayList<TurnE>());
        roundE2.setGame(gameE2);

        TurnE turnE3 = new TurnE();
        turnE3.setRound(new RoundE());
        turnE3.setFeedback(new FeedbackE());
        turnE3.setGuess("Guess");
        turnE3.setHint(new HintE());
        turnE3.setTurnCount(3);

        FeedbackE feedbackE2 = new FeedbackE();
        feedbackE2.setMarks(new ArrayList<Mark>());
        feedbackE2.setAttempt("Attempt");
        feedbackE2.setTurnE(turnE3);

        TurnE turnE4 = new TurnE();
        turnE4.setRound(new RoundE());
        turnE4.setFeedback(new FeedbackE());
        turnE4.setGuess("Guess");
        turnE4.setHint(new HintE());
        turnE4.setTurnCount(3);

        HintE hintE1 = new HintE();
        hintE1.setHintList(new ArrayList<Character>());
        hintE1.setTurnE(turnE4);

        TurnE turnE5 = new TurnE();
        turnE5.setRound(roundE2);
        turnE5.setFeedback(feedbackE2);
        turnE5.setGuess("Guess");
        turnE5.setHint(hintE1);
        turnE5.setTurnCount(3);

        HintE hintE2 = new HintE();
        hintE2.setHintList(new ArrayList<Character>());
        hintE2.setTurnE(turnE5);

        TurnE turnE6 = new TurnE();
        turnE6.setRound(roundE);
        turnE6.setFeedback(feedbackE1);
        turnE6.setGuess("Guess");
        turnE6.setHint(hintE2);
        turnE6.setTurnCount(3);
        when(this.turnRepository.save((TurnE) any())).thenReturn(turnE6);

        GameE gameE3 = new GameE();
        gameE3.setRound(new ArrayList<RoundE>());
        gameE3.setScore(3);

        RoundE roundE3 = new RoundE();
        roundE3.setWord(new Word());
        roundE3.setTurns(new ArrayList<TurnE>());
        roundE3.setGame(gameE3);

        RoundE roundE4 = new RoundE();
        roundE4.setWord(new Word());
        roundE4.setTurns(new ArrayList<TurnE>());
        roundE4.setGame(new GameE());

        FeedbackE feedbackE3 = new FeedbackE();
        feedbackE3.setMarks(new ArrayList<Mark>());
        feedbackE3.setAttempt("Attempt");
        feedbackE3.setTurnE(new TurnE());

        HintE hintE3 = new HintE();
        hintE3.setHintList(new ArrayList<Character>());
        hintE3.setTurnE(new TurnE());

        TurnE turnE7 = new TurnE();
        turnE7.setRound(roundE4);
        turnE7.setFeedback(feedbackE3);
        turnE7.setGuess("Guess");
        turnE7.setHint(hintE3);
        turnE7.setTurnCount(3);

        FeedbackE feedbackE4 = new FeedbackE();
        feedbackE4.setMarks(new ArrayList<Mark>());
        feedbackE4.setAttempt("Attempt");
        feedbackE4.setTurnE(turnE7);

        RoundE roundE5 = new RoundE();
        roundE5.setWord(new Word());
        roundE5.setTurns(new ArrayList<TurnE>());
        roundE5.setGame(new GameE());

        FeedbackE feedbackE5 = new FeedbackE();
        feedbackE5.setMarks(new ArrayList<Mark>());
        feedbackE5.setAttempt("Attempt");
        feedbackE5.setTurnE(new TurnE());

        HintE hintE4 = new HintE();
        hintE4.setHintList(new ArrayList<Character>());
        hintE4.setTurnE(new TurnE());

        TurnE turnE8 = new TurnE();
        turnE8.setRound(roundE5);
        turnE8.setFeedback(feedbackE5);
        turnE8.setGuess("Guess");
        turnE8.setHint(hintE4);
        turnE8.setTurnCount(3);

        HintE hintE5 = new HintE();
        hintE5.setHintList(new ArrayList<Character>());
        hintE5.setTurnE(turnE8);

        TurnE turnE9 = new TurnE();
        turnE9.setRound(roundE3);
        turnE9.setFeedback(feedbackE4);
        turnE9.setGuess("Guess");
        turnE9.setHint(hintE5);
        turnE9.setTurnCount(3);

        HintE hintE6 = new HintE();
        hintE6.setHintList(new ArrayList<Character>());
        hintE6.setTurnE(turnE9);
        when(this.hintService.giveHint((TurnE) any(), (List<Character>) any())).thenReturn(hintE6);
        when(this.feedbackService.giveFeedback((List<Mark>) any(), (RoundE) any(), (TurnE) any()))
                .thenReturn(new ArrayList<Mark>());

        GameE gameE4 = new GameE();
        ArrayList<RoundE> roundEList = new ArrayList<RoundE>();
        gameE4.setRound(roundEList);
        gameE4.setScore(3);

        RoundE roundE6 = new RoundE();
        roundE6.setWord(new Word());
        roundE6.setTurns(new ArrayList<TurnE>());
        roundE6.setGame(gameE4);
        TurnE actualStartTurnResult = this.turnService.startTurn(roundE6, "Guess");
        assertEquals(1, actualStartTurnResult.getTurnCount());
        assertEquals("Guess", actualStartTurnResult.getGuess());
        RoundE round = actualStartTurnResult.getRound();
        assertSame(roundE6, round);
        assertSame(hintE6, actualStartTurnResult.getHint());
        FeedbackE feedback = actualStartTurnResult.getFeedback();
        assertEquals(roundEList, feedback.getMarks());
        assertSame(actualStartTurnResult, feedback.getTurnE());
        assertEquals("Guess", feedback.getAttempt());
        assertEquals(1, round.getTurns().size());
        verify(this.turnRepository).save((TurnE) any());
        verify(this.hintService).giveHint((TurnE) any(), (List<Character>) any());
        verify(this.feedbackService).giveFeedback((List<Mark>) any(), (RoundE) any(), (TurnE) any());
    }

    @Test
    void testGetTurnById() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
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
        Optional<TurnE> ofResult = Optional.<TurnE>of(turnE2);
        when(this.turnRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(turnE2, this.turnService.getTurnById(123L));
        verify(this.turnRepository).findById((Long) any());
    }

    @Test
    void testGetTurnById2() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
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
        Optional<TurnE> ofResult = Optional.<TurnE>of(turnE2);
        when(this.turnRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(turnE2, this.turnService.getTurnById(123L));
        verify(this.turnRepository).findById((Long) any());
    }

    @Test
    void testGetTurnById3() {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
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
        Optional<TurnE> ofResult = Optional.<TurnE>of(turnE2);
        when(this.turnRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(turnE2, this.turnService.getTurnById(123L));
        verify(this.turnRepository).findById((Long) any());
    }

    @Test
    void testGuessWord() {

    }

}
