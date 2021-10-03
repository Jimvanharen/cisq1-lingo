package nl.hu.cisq1.lingo.trainer.domain;

public class Game {

    private Round round;
    private int score;

    public Game(Round round, int score){
        this.round = round;
        this.score = score;
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

    public Round getRound() {
        return round;
    }
}
