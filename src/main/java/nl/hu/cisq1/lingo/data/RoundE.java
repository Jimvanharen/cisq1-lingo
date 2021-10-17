package nl.hu.cisq1.lingo.data;



import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "round")
public class RoundE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Word word;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameE game;

    @OneToMany(mappedBy = "round")
    private List<TurnE> turns;

    public RoundE(){}

    public RoundE(Word word, GameE game ,List<TurnE> turns){
        this.game = game;
        this.word = word;
        this.turns = turns;
    }

    public void addTurn(TurnE turnE){
        turns.add(turnE);
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public GameE getGame() {
        return game;
    }

    public void setGame(GameE game) {
        this.game = game;
    }

    public List<TurnE> getTurns() {
        return turns;
    }

    public void setTurns(List<TurnE> turns) {
        this.turns = turns;
    }
}
