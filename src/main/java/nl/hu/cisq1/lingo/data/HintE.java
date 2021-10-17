package nl.hu.cisq1.lingo.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hint")
public class HintE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TurnE turnE;

    @ElementCollection
    private List<Character> c;

    public HintE(){}

    public HintE(TurnE turnE){
        this.turnE = turnE;
    }
}
