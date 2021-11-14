package nl.hu.cisq1.lingo.trainer.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Word word;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @JsonIgnore
    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<Turn> turns;

    public Round(){}

    public Round(Word word, Game game, List<Turn> turns){
        this.game = game;
        this.word = word;
        this.turns = turns;
    }

    public boolean addTurn(Turn turn){
        turn.setTurnCount(turn.getTurnCount() + 1);
        if(turn.getTurnCount() <= 5){
            return turns.add(turn);

        }
        else{
            return false;
        }
    }
    public Round startRound(Game game, String toBeGuessedWord){
        Round round = new Round(new Word(toBeGuessedWord), game, new ArrayList<>());
        game.addRound(round);
        return round;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Game getGame() {
        return game;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
