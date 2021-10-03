package nl.hu.cisq1.lingo.trainer.domain;

public class Round {

    private Word word;
    private Game game;
    private final static int MAX_ATTEMPTS = 5;

    public Round(Word word, Game game){
        this.word = word;
        this.game = game;
    }

    public static int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public Word getWord() {
        return word;
    }

    public Game getGame() {
        return game;
    }
}
