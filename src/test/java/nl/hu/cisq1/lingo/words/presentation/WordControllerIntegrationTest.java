package nl.hu.cisq1.lingo.words.presentation;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
class WordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("only supports 5, 6 and 7 letter words")
    void notSupportedWordLength() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/words/random")
                .param("length", "8");

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("provides random 5, 6, and 7 letter words")
    void provideWords() throws Exception {
        for (int length = 5; length <= 7; length++) {
            RequestBuilder request = MockMvcRequestBuilders
                    .get("/words/random")
                    .param("length", String.valueOf(length));

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string(hasLength(length)));
        }
    }
}
