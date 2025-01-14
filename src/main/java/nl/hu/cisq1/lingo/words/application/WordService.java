package nl.hu.cisq1.lingo.words.application;

import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class WordService {
    private final SpringWordRepository wordRepository;

    public WordService(SpringWordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public String provideRandomWord(Integer length) {
        return this.wordRepository
                .findRandomWordByLength(length)
                .orElseThrow(() -> new WordLengthNotSupportedException(length))
                .getValue();
    }

    public String provideRandomWordByRandomLength(){

        return provideRandomWord((int) Math.floor(Math.random() * (6-5 + 1) + 5));
    }
}