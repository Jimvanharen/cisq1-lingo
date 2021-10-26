package nl.hu.cisq1.lingo.presentation;


import java.util.ArrayList;

import nl.hu.cisq1.lingo.application.GameService;
import nl.hu.cisq1.lingo.application.RoundService;
import nl.hu.cisq1.lingo.data.GameE;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import org.assertj.core.error.ShouldBeEqual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import nl.hu.cisq1.lingo.CiTestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private GameController gameController;

    @MockBean
    private GameService gameService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testStartGame() throws Exception {
        GameE gameE = new GameE();
        gameE.setRound(new ArrayList<RoundE>());
        gameE.setScore(3);
        when(this.gameService.startGame()).thenReturn(gameE);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/game/start-game");
        MockMvcBuilders.standaloneSetup(this.gameController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Game Created"));
    }
}
