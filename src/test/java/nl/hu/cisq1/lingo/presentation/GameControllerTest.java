package nl.hu.cisq1.lingo.presentation;


import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import org.assertj.core.error.ShouldBeEqual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import nl.hu.cisq1.lingo.CiTestConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Start game test")
    void startNewGame() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/game/start-game");

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.score", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.gamestatus",
                        org.hamcrest.Matchers.equalTo(GameStatus.PAUSED)));
    }
}
