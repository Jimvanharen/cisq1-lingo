package nl.hu.cisq1.lingo.trainer.domain;

public class Word {

    private String toBeGuessedWord;

    public Word(String toBeGuessedWord){
        this.toBeGuessedWord = toBeGuessedWord;
    }

    public String getToBeGuessedWord() {
        return toBeGuessedWord;
    }
}
