package nl.hu.cisq1.lingo.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.hu.cisq1.lingo.application.TrainerService;
import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
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

@ContextConfiguration(classes = {RoundController.class})
@ExtendWith(SpringExtension.class)
class RoundControllerTest {
    @Autowired
    private RoundController roundController;

    @MockBean
    private TrainerService trainerService;

    @MockBean
    private WordService wordService;

    @Test
    void testStartRoundById() {
        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.findById((Long) any())).thenReturn(Optional.<Game>empty());
        TurnRepository turnRepository = mock(TurnRepository.class);
        RoundRepository roundRepository = mock(RoundRepository.class);
        TrainerService trainerService = new TrainerService(turnRepository, roundRepository, gameRepository,
                new WordService(mock(SpringWordRepository.class)), mock(FeedbackRepository.class));

        SpringWordRepository springWordRepository = mock(SpringWordRepository.class);
        when(springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(Optional.<Word>of(new Word()));
        ResponseEntity<?> actualStartRoundByIdResult = (new RoundController(trainerService,
                new WordService(springWordRepository))).startRoundById(123L);
        assertEquals("Failed to start round", actualStartRoundByIdResult.getBody());
        assertEquals("<409 CONFLICT Conflict,Failed to start round,[]>", actualStartRoundByIdResult.toString());
        assertEquals(HttpStatus.CONFLICT, actualStartRoundByIdResult.getStatusCode());
        assertTrue(actualStartRoundByIdResult.getHeaders().isEmpty());
        verify(gameRepository).findById((Long) any());
        verify(springWordRepository).findRandomWordByLength((Integer) any());
    }

    @Test
    void testStartRound() {
        GameRepository gameRepository = mock(GameRepository.class);
        when(gameRepository.findById((Long) any())).thenReturn(Optional.<Game>empty());
        when(gameRepository.maxId()).thenReturn(Optional.<Long>of(42L));
        TurnRepository turnRepository = mock(TurnRepository.class);
        RoundRepository roundRepository = mock(RoundRepository.class);
        TrainerService trainerService = new TrainerService(turnRepository, roundRepository, gameRepository,
                new WordService(mock(SpringWordRepository.class)), mock(FeedbackRepository.class));

        SpringWordRepository springWordRepository = mock(SpringWordRepository.class);
        when(springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(Optional.<Word>of(new Word()));
        ResponseEntity<?> actualStartRoundResult = (new RoundController(trainerService,
                new WordService(springWordRepository))).startRound();
        assertEquals("Failed to start round", actualStartRoundResult.getBody());
        assertEquals("<409 CONFLICT Conflict,Failed to start round,[]>", actualStartRoundResult.toString());
        assertEquals(HttpStatus.CONFLICT, actualStartRoundResult.getStatusCode());
        assertTrue(actualStartRoundResult.getHeaders().isEmpty());
        verify(gameRepository).findById((Long) any());
        verify(gameRepository).maxId();
        verify(springWordRepository).findRandomWordByLength((Integer) any());
    }

    @Test
    void testRemoveRound() throws Exception {
        doNothing().when(this.trainerService).removeRound((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/round/delete-round/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.roundController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Succesfully deleted round with id: 123"));
    }
}

