package nl.hu.cisq1.lingo.trainer.domain;

import java.util.Scanner;

public class Round {

    private Word word;
    private Game game;
    private Turn turn;
    private final static int MAX_ATTEMPTS = 5;

    public Round(Word word, Game game, Turn turn){
        this.word = word;
        this.game = game;
        this.turn = turn;
    }

    public void startRound(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter attempt: ");
        String attempt = scanner.next();
        turn.startTurn(attempt);
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

    public Turn getTurn() {
        return turn;
    }
}
