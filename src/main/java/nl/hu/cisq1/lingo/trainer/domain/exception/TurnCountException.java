package nl.hu.cisq1.lingo.trainer.domain.exception;

public class TurnCountException extends RuntimeException {

    public TurnCountException() {
        super("Round boundary exception, cannot have more than 5 turns in 1 round!");
    }

}
