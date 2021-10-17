package nl.hu.cisq1.lingo.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feedback")
public class FeedbackE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<MarkE> marks;

    @Column
    private String attempt;

    public FeedbackE(){}

    public FeedbackE(List<MarkE> marks, String attempt){
        this.attempt = attempt;
    }
}
