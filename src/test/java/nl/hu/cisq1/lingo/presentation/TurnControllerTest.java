package nl.hu.cisq1.lingo.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.hu.cisq1.lingo.application.TrainerService;
import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Hint;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.Turn;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TurnController.class})
@ExtendWith(SpringExtension.class)
class TurnControllerTest {
    @MockBean
    private TrainerService trainerService;

    @Autowired
    private TurnController turnController;

    @Test
    void testGetTurnsByLastRound() throws Exception {
        when(this.trainerService.getAllTurnsOfLastRound()).thenReturn(new ArrayList<Turn>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/turn/turns");
        MockMvcBuilders.standaloneSetup(this.turnController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testStartTurn() throws Exception {
        Game gameE = new Game();
        gameE.setRound(new ArrayList<>());
        gameE.setScore(3);

        Round round = new Round();
        round.setWord(new Word());
        round.setTurns(new ArrayList<>());
        round.setGame(gameE);

        Game gameE1 = new Game();
        gameE1.setRound(new ArrayList<>());
        gameE1.setScore(3);

        Round roundE1 = new Round();
        roundE1.setWord(new Word());
        roundE1.setTurns(new ArrayList<>());
        roundE1.setGame(gameE1);

        Turn turnE = new Turn();
        turnE.setRound(new Round());
        turnE.setFeedback(new Feedback());
        turnE.setGuess("Guess");
        turnE.setHint(new Hint());
        turnE.setTurnCount(3);

        Feedback feedbackE = new Feedback();
        feedbackE.setMarks(new ArrayList<>());
        feedbackE.setAttempt("Attempt");
        feedbackE.setTurnE(turnE);

        Turn turnE1 = new Turn();
        turnE1.setRound(new Round());
        turnE1.setFeedback(new Feedback());
        turnE1.setGuess("Guess");
        turnE1.setHint(new Hint());
        turnE1.setTurnCount(3);

        Hint hintE = new Hint();
        hintE.setHintList(new ArrayList<Character>());
        hintE.setTurnE(turnE1);

        Turn turnE2 = new Turn();
        turnE2.setRound(roundE1);
        turnE2.setFeedback(feedbackE);
        turnE2.setGuess("Guess");
        turnE2.setHint(hintE);
        turnE2.setTurnCount(3);

        Feedback feedbackE1 = new Feedback();
        feedbackE1.setMarks(new ArrayList<Mark>());
        feedbackE1.setAttempt("Attempt");
        feedbackE1.setTurnE(turnE2);

        Game gameE2 = new Game();
        gameE2.setRound(new ArrayList<>());
        gameE2.setScore(3);

        Round roundE2 = new Round();
        roundE2.setWord(new Word());
        roundE2.setTurns(new ArrayList<>());
        roundE2.setGame(gameE2);

        Turn turnE3 = new Turn();
        turnE3.setRound(new Round());
        turnE3.setFeedback(new Feedback());
        turnE3.setGuess("Guess");
        turnE3.setHint(new Hint());
        turnE3.setTurnCount(3);

        Feedback feedbackE2 = new Feedback();
        feedbackE2.setMarks(new ArrayList<Mark>());
        feedbackE2.setAttempt("Attempt");
        feedbackE2.setTurnE(turnE3);

        Turn turnE4 = new Turn();
        turnE4.setRound(new Round());
        turnE4.setFeedback(new Feedback());
        turnE4.setGuess("Guess");
        turnE4.setHint(new Hint());
        turnE4.setTurnCount(3);

        Hint hintE1 = new Hint();
        hintE1.setHintList(new ArrayList<Character>());
        hintE1.setTurnE(turnE4);

        Turn turnE5 = new Turn();
        turnE5.setRound(roundE2);
        turnE5.setFeedback(feedbackE2);
        turnE5.setGuess("Guess");
        turnE5.setHint(hintE1);
        turnE5.setTurnCount(3);

        Hint hintE2 = new Hint();
        hintE2.setHintList(new ArrayList<Character>());
        hintE2.setTurnE(turnE5);

        Turn turnE6 = new Turn();
        turnE6.setRound(round);
        turnE6.setFeedback(feedbackE1);
        turnE6.setGuess("Guess");
        turnE6.setHint(hintE2);
        turnE6.setTurnCount(3);
        when(this.trainerService.startTurn((Round) any(), (String) any())).thenReturn(turnE6);

        Game gameE3 = new Game();
        gameE3.setRound(new ArrayList<>());
        gameE3.setScore(3);

        Round roundE3 = new Round();
        roundE3.setWord(new Word());
        roundE3.setTurns(new ArrayList<>());
        roundE3.setGame(gameE3);
        when(this.trainerService.getRoundById((Long) any())).thenReturn(roundE3);
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

