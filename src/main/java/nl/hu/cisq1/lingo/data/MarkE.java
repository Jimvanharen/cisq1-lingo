package nl.hu.cisq1.lingo.data;
import nl.hu.cisq1.lingo.trainer.domain.Mark;

import javax.persistence.*;

@Entity
@Table(name = "mark")
public class MarkE {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Mark mark;

    public MarkE(){}

    public MarkE(Mark mark){
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
