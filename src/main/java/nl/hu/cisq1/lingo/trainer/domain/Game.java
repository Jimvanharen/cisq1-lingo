package nl.hu.cisq1.lingo.trainer.domain;

public class Game {

    private Long id;

    private Round round;

    private int score;

    private static final int MAX_ROUNDS = 5;

    public Game(Round round, int score){
        this.round = round;
        this.score = score;
    }

    public void startGame(){
        round.startRound();
    }
    public void setRound(Round round) {
        this.round = round;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public static int getMaxRounds() {
        return MAX_ROUNDS;
    }

    public Round getRound() {
        return round;
    }
}
