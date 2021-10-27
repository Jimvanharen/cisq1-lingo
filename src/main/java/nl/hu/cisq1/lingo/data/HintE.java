package nl.hu.cisq1.lingo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hint")
public class HintE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "hint")
    private TurnE turnE;

    @ElementCollection
    private List<Character> hintList;

    public HintE(){}

    public HintE(List<Character> hintList, TurnE turnE){
        this.turnE = turnE;
        this.hintList = hintList;
    }

    public TurnE getTurnE() {
        return turnE;
    }

    public void setTurnE(TurnE turnE) {
        this.turnE = turnE;
    }

    public List<Character> getHintList() {
        return hintList;
    }

    public void setHintList(List<Character> hintList) {
        this.hintList = hintList;
    }
}
