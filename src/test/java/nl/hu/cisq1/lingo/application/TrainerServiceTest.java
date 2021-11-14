package nl.hu.cisq1.lingo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.hu.cisq1.lingo.data.FeedbackRepository;
import nl.hu.cisq1.lingo.data.GameRepository;
import nl.hu.cisq1.lingo.data.RoundRepository;
import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TrainerService.class, WordService.class})
@ExtendWith(SpringExtension.class)
class TrainerServiceTest {
    @MockBean
    private FeedbackRepository feedbackRepository;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private RoundRepository roundRepository;

    @Autowired
    private TrainerService trainerService;

    @MockBean
    private TurnRepository turnRepository;

    @MockBean
    private WordService wordService;

    @Test
    void testConstructor() {
        TurnRepository turnRepository = mock(TurnRepository.class);
        RoundRepository roundRepository = mock(RoundRepository.class);
        GameRepository gameRepository = mock(GameRepository.class);
        TrainerService actualTrainerService = new TrainerService(turnRepository, roundRepository, gameRepository,
                new WordService(mock(SpringWordRepository.class)), mock(FeedbackRepository.class));

        assertNull(actualTrainerService.getMaxId());
        assertNull(actualTrainerService.getRoundMaxId());
    }

    @Test
    void testStartGame() {
        when(this.wordService.provideRandomWord((Integer) any())).thenReturn("Provide Random Word");

        Game game = new Game();
        game.setRound(new ArrayList<Round>());
        game.setScore(3);
        game.setGamestatus(GameStatus.ENDED);

        Round round = new Round();
        round.setWord(new Word());
        ArrayList<Turn> turnList = new ArrayList<Turn>();
        round.setTurns(turnList);
        round.setGame(game);
        when(this.roundRepository.save((Round) any())).thenReturn(round);

        Game game1 = new Game();
        game1.setRound(new ArrayList<Round>());
        game1.setScore(3);
        game1.setGamestatus(GameStatus.ENDED);
        when(this.gameRepository.save((Game) any())).thenReturn(game1);
        Game actualStartGameResult = this.trainerService.startGame();
        assertEquals(GameStatus.PLAYING, actualStartGameResult.getGamestatus());
        assertEquals(0, actualStartGameResult.getScore());
        List<Round> rounds = actualStartGameResult.getRounds();
        assertEquals(2, rounds.size());
        Round getResult = rounds.get(0);
        List<Turn> turns = getResult.getTurns();
        assertEquals(1, turns.size());
        assertSame(actualStartGameResult, getResult.getGame());
        Word word = getResult.getWord();
        assertEquals(19, word.getLength().intValue());
        assertEquals("Provide Random Word", word.getValue());
        Turn getResult1 = turns.get(0);
        assertSame(getResult, getResult1.getRound());
        assertEquals("", getResult1.getGuess());
        assertEquals(1, getResult1.getTurnCount());
        Hint hint = getResult1.getHint();
        List<Character> hintList = hint.getHintList();
        assertEquals(19, hintList.size());
        assertEquals('P', hintList.get(0));
        assertEquals('.', hintList.get(1));
        assertEquals('.', hintList.get(17));
        assertEquals('.', hintList.get(18));
        Feedback feedback = getResult1.getFeedback();
        assertSame(getResult1, feedback.getTurnE());
        assertEquals(turnList, feedback.getMarks());
        assertEquals("", feedback.getAttempt());
        assertSame(getResult1, hint.getTurnE());
        verify(this.wordService).provideRandomWord((Integer) any());
        verify(this.roundRepository).save((Round) any());
        verify(this.gameRepository).save((Game) any());
        assertNull(this.trainerService.getMaxId());
        assertNull(this.trainerService.getRoundMaxId());
    }

    @Test
    void testGetGameById() {
        Game game = new Game();
        game.setRound(new ArrayList<Round>());
        game.setScore(3);
        game.setGamestatus(GameStatus.ENDED);
        Optional<Game> ofResult = Optional.<Game>of(game);
        when(this.gameRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(game, this.trainerService.getGameById(123L));
        verify(this.gameRepository).findById((Long) any());
        assertNull(this.trainerService.getMaxId());
        assertNull(this.trainerService.getRoundMaxId());
    }

    @Test
    void testStartRound() {
        Game game = new Game();
        game.setRound(new ArrayList<Round>());
        game.setScore(3);
        game.setGamestatus(GameStatus.ENDED);

        Round round = new Round();
        round.setWord(new Word());
        ArrayList<Turn> turnList = new ArrayList<Turn>();
        round.setTurns(turnList);
        round.setGame(game);
        when(this.roundRepository.save((Round) any())).thenReturn(round);

        Game game1 = new Game();
        game1.setRound(new ArrayList<Round>());
        game1.setScore(3);
        game1.setGamestatus(GameStatus.ENDED);
        Round actualStartRoundResult = this.trainerService.startRound(game1, "To Be Guessed Word");
        Game game2 = actualStartRoundResult.getGame();
        assertSame(game1, game2);
        List<Turn> turns = actualStartRoundResult.getTurns();
        assertEquals(1, turns.size());
        Word word = actualStartRoundResult.getWord();
        assertEquals("To Be Guessed Word", word.getValue());
        assertEquals(18, word.getLength().intValue());
        assertEquals(1, game2.getRounds().size());
        Turn getResult = turns.get(0);
        assertEquals("", getResult.getGuess());
        assertEquals(1, getResult.getTurnCount());
        assertSame(actualStartRoundResult, getResult.getRound());
        Feedback feedback = getResult.getFeedback();
        assertEquals("", feedback.getAttempt());
        Hint hint = getResult.getHint();
        assertSame(getResult, hint.getTurnE());
        List<Character> hintList = hint.getHintList();
        assertEquals(18, hintList.size());
        assertEquals('T', hintList.get(0));
        assertEquals('.', hintList.get(1));
        assertEquals('.', hintList.get(Short.SIZE));
        assertEquals('.', hintList.get(17));
        assertEquals(turnList, feedback.getMarks());
        assertSame(getResult, feedback.getTurnE());
        verify(this.roundRepository).save((Round) any());
        assertNull(this.trainerService.getMaxId());
        assertNull(this.trainerService.getRoundMaxId());
    }

    @Test
    void testStartTurn() {
        Game game = new Game();
        game.setRound(new ArrayList<Round>());
        game.setScore(3);
        game.setGamestatus(GameStatus.ENDED);

        Round round = new Round();
        round.setWord(new Word("Word"));
        round.setTurns(new ArrayList<Turn>());
        round.setGame(game);
        Round round1 = this.trainerService.startTurn(round, "Guess").getRound();
        assertSame(round, round1);
        List<Turn> turns = round1.getTurns();
        assertEquals(1, turns.size());
        Turn getResult = turns.get(0);
        assertEquals(1, getResult.getTurnCount());
        assertSame(round1, getResult.getRound());
        assertEquals("Guess", getResult.getGuess());
        Hint hint = getResult.getHint();
        List<Character> hintList = hint.getHintList();
        assertEquals(4, hintList.size());
        assertEquals('W', hintList.get(0));
        assertEquals('.', hintList.get(1));
        assertEquals('.', hintList.get(2));
        assertEquals('.', hintList.get(3));
        Feedback feedback = getResult.getFeedback();
        assertSame(getResult, feedback.getTurnE());
        List<Mark> marks = feedback.getMarks();
        assertEquals(5, marks.size());
        assertEquals(Mark.INVALID, marks.get(0));
        assertEquals(Mark.INVALID, marks.get(1));
        assertEquals(Mark.INVALID, marks.get(3));
        assertEquals(Mark.INVALID, marks.get(4));
        assertEquals("Guess", feedback.getAttempt());
        assertSame(getResult, hint.getTurnE());
    }

    @Test
    void testGetAllTurnsOfLastRound() {
        Game game = new Game();
        game.setRound(new ArrayList<Round>());
        game.setScore(3);
        game.setGamestatus(GameStatus.ENDED);

        Round round = new Round();
        round.setWord(new Word());
        ArrayList<Turn> turnList = new ArrayList<Turn>();
        round.setTurns(turnList);
        round.setGame(game);
        Optional<Round> ofResult = Optional.<Round>of(round);
        when(this.roundRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.roundRepository.maxId()).thenReturn(Optional.<Long>of(42L));
        List<Turn> actualAllTurnsOfLastRound = this.trainerService.getAllTurnsOfLastRound();
        assertSame(turnList, actualAllTurnsOfLastRound);
        assertTrue(actualAllTurnsOfLastRound.isEmpty());
        verify(this.roundRepository).findById((Long) any());
        verify(this.roundRepository).maxId();
        assertNull(this.trainerService.getMaxId());
        assertEquals(42L, this.trainerService.getRoundMaxId().longValue());
    }

    @Test
    void testRemoveRound() {
        Game game = new Game();
        game.setRound(new ArrayList<Round>());
        game.setScore(3);
        game.setGamestatus(GameStatus.ENDED);

        Round round = new Round();
        round.setWord(new Word());
        round.setTurns(new ArrayList<Turn>());
        round.setGame(game);
        Optional<Round> ofResult = Optional.<Round>of(round);
        doNothing().when(this.roundRepository).delete((Round) any());
        when(this.roundRepository.findById((Long) any())).thenReturn(ofResult);
        this.trainerService.removeRound(123L);
        verify(this.roundRepository).delete((Round) any());
        verify(this.roundRepository).findById((Long) any());
        assertNull(this.trainerService.getRoundMaxId());
        assertNull(this.trainerService.getMaxId());
    }

    @Test
    void testGetRoundMaxId() {
        when(this.roundRepository.maxId()).thenReturn(Optional.<Long>of(42L));
        assertEquals(42L, this.trainerService.getRoundMaxId().longValue());
        verify(this.roundRepository).maxId();
        assertNull(this.trainerService.getMaxId());
    }

    @Test
    void testGetMaxId() {
        when(this.gameRepository.maxId()).thenReturn(Optional.<Long>of(42L));
        assertEquals(42L, this.trainerService.getMaxId().longValue());
        verify(this.gameRepository).maxId();
        assertNull(this.trainerService.getRoundMaxId());
    }

    @Test
    void testGetRoundById() {
        Game game = new Game();
        game.setRound(new ArrayList<Round>());
        game.setScore(3);
        game.setGamestatus(GameStatus.ENDED);

        Round round = new Round();
        round.setWord(new Word());
        round.setTurns(new ArrayList<Turn>());
        round.setGame(game);
        Optional<Round> ofResult = Optional.<Round>of(round);
        when(this.roundRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(round, this.trainerService.getRoundById(123L));
        verify(this.roundRepository).findById((Long) any());
        assertNull(this.trainerService.getRoundMaxId());
        assertNull(this.trainerService.getMaxId());
    }
}

