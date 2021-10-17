package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.application.RoundService;
import nl.hu.cisq1.lingo.application.TurnService;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.TurnE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/turn")
public class TurnController {

    private TurnService turnService;
    private RoundService roundService;

    public TurnController(TurnService turnService, RoundService roundService){
        this.turnService = turnService;
        this.roundService = roundService;
    }

    @PostMapping("/startTurn/{id}")
    public ResponseEntity<?> startTurn(@PathVariable Long id){
        try{
            turnService.startTurn(roundService.getRoundById(id));
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong submitting the turn", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Turn submitted...", HttpStatus.OK);
    }
}
