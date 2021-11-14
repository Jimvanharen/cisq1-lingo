package nl.hu.cisq1.lingo.trainer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<Round> round;

    @Enumerated(EnumType.STRING)
    private GameStatus gamestatus;

    @Column
    private int score;

    public Game(){}

    public Game(List<Round> rounds, int score, GameStatus gameStatus){
        this.round = rounds;
        this.score = score;
        this.gamestatus = gameStatus;
    }

    public boolean addRound(Round round){
        return this.round.add(round);
    }

    public int getScore() {
        return score;
    }

    public GameStatus getGamestatus(){
        return this.gamestatus;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Round> getRounds() {
        return round;
    }

    public void setGamestatus(GameStatus gamestatus) {
        this.gamestatus = gamestatus;
    }

    public void setRound(List<Round> round) {
        this.round = round;
    }
}
