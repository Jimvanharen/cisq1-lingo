package nl.hu.cisq1.lingo.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
public class GameE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game")
    private List<RoundE> round;

    @Column
    private int score;

    public GameE(){}

    public GameE(List<RoundE> rounds, int score){
        this.round = rounds;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
