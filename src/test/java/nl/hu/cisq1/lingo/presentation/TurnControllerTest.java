package nl.hu.cisq1.lingo.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.hu.cisq1.lingo.application.FeedbackService;
import nl.hu.cisq1.lingo.application.HintService;
import nl.hu.cisq1.lingo.application.RoundService;
import nl.hu.cisq1.lingo.application.TurnService;
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
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ContextConfiguration(classes = {TurnController.class})
@ExtendWith(SpringExtension.class)
class TurnControllerTest {

    @MockBean
    private RoundService roundService;

    @Autowired
    private TurnController turnController;

    @MockBean
    private TurnService turnService;

    @Test
    void testStartTurn() throws Exception {
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
        when(this.turnService.startTurn((RoundE) any(), (String) any())).thenReturn(turnE6);

        GameE gameE3 = new GameE();
        gameE3.setRound(new ArrayList<RoundE>());
        gameE3.setScore(3);

        RoundE roundE3 = new RoundE();
        roundE3.setWord(new Word());
        roundE3.setTurns(new ArrayList<TurnE>());
        roundE3.setGame(gameE3);
        when(this.roundService.getRoundById((Long) any())).thenReturn(roundE3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/turn/start-turn/{id}", 123L)
                .param("guess", "foo");
        MockMvcBuilders.standaloneSetup(this.turnController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Turn submitted..."));
    }
}

