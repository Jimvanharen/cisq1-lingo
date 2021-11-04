package nl.hu.cisq1.lingo.presentation;

import javassist.NotFoundException;
import nl.hu.cisq1.lingo.application.RoundService;
import nl.hu.cisq1.lingo.application.TurnService;
import nl.hu.cisq1.lingo.data.RoundE;
import nl.hu.cisq1.lingo.data.TurnE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turn")
public class TurnController {

    private TurnService turnService;
    private RoundService roundService;

    public TurnController(TurnService turnService, RoundService roundService){
        this.turnService = turnService;
        this.roundService = roundService;
    }

    @PostMapping("/start-turn/{id}")
    public ResponseEntity<?> startTurn(@PathVariable Long id, @RequestParam String guess){
        try{
            turnService.startTurn(roundService.getRoundById(id), guess);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong submitting the turn", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Turn submitted...", HttpStatus.OK);
    }

    @PostMapping("/start-turn")
    public ResponseEntity<?> startTurn(@RequestParam String guess){
        try{
            turnService.startTurn(roundService.getRoundById(roundService.getRoundMaxId()), guess);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong submitting the turn", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Turn submitted...", HttpStatus.OK);
    }

    @GetMapping("/turns")
    public ResponseEntity<List<TurnE>> getTurnsByLastRound(){
        List<TurnE> turnES;
        try{
            turnES = roundService.getAllTurnsOfLastRound();
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(List.of(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(turnES, HttpStatus.OK);
    }
}
