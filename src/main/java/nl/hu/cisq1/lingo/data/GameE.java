package nl.hu.cisq1.lingo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
public class GameE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<RoundE> round;

    @Enumerated(EnumType.STRING)
    private GameStatus gamestatus;

    @Column
    private int score;

    public GameE(){}

    public GameE(List<RoundE> rounds, int score, GameStatus gameStatus){
        this.round = rounds;
        this.score = score;
        this.gamestatus = gameStatus;
    }

    public void addRound(RoundE roundE){
        this.round.add(roundE);
    }

    public List<RoundE> getRound() {
        return round;
    }

    public void setRound(List<RoundE> round) {
        this.round = round;
    }

    public GameStatus getGamestatus() {
        return gamestatus;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
