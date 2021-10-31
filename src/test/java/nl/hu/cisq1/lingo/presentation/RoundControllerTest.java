package nl.hu.cisq1.lingo.presentation;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import nl.hu.cisq1.lingo.application.FeedbackService;
import nl.hu.cisq1.lingo.application.GameService;
import nl.hu.cisq1.lingo.application.HintService;
import nl.hu.cisq1.lingo.application.RoundService;
import nl.hu.cisq1.lingo.application.TurnService;
import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.HintRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnE;
import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ContextConfiguration(classes = {RoundController.class})
@ExtendWith(SpringExtension.class)
class RoundControllerTest {
    @MockBean
    private GameService gameService;

    @Autowired
    private RoundController roundController;

    @MockBean
    private RoundService roundService;

    @MockBean
    private WordService wordService;

    @Test
    void testStartRound() throws Exception {
        when(this.wordService.provideRandomWordByRandomLength()).thenReturn("Provide Random Word By Random Length");

        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);

        RoundE roundE = new RoundE();
        roundE.setWord(new Word());
        roundE.setTurns(new ArrayList<TurnE>());
        roundE.setGame(gameE);
        when(this.roundService.startRound((GameE) any(), (String) any())).thenReturn(roundE);

        GameE gameE1 = new GameE();
        gameE1.setRound(new ArrayList<RoundE>());
        gameE1.setScore(3);
        when(this.gameService.getGameById((Long) any())).thenReturn(gameE1);
        when(this.gameService.getMaxId()).thenReturn(123L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/round/start-round");
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Started round on game with id: 123"));
    }

    @Test
    void testRemoveRound() throws Exception {
        doNothing().when(this.roundService).removeRound((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/round/delete-round/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Succesfully deleted round with id: 123"));
    }
}

