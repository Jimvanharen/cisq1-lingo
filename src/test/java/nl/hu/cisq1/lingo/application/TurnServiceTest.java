package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.TurnRepository;
import nl.hu.cisq1.lingo.trainer.domain.Turn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TurnServiceTest {

    @MockBean
    private TurnRepository turnRepository;

    @Autowired
    private TurnService turnService;

    @Test
    void testGuessWord(){

    }

}
