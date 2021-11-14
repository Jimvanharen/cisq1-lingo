package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.Generated;
import nl.hu.cisq1.lingo.application.TrainerService;
import nl.hu.cisq1.lingo.trainer.domain.Turn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turn")
public class TurnController {

    private final TrainerService trainerService;

    public TurnController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @Generated
    @PostMapping("/start-turn/{id}")
    public ResponseEntity<?> startTurn(@PathVariable Long id, @RequestParam String guess){
        try{
            trainerService.startTurn(trainerService.getRoundById(id), guess);
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
            trainerService.startTurn(trainerService.getRoundById(trainerService.getRoundMaxId()), guess);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong submitting the turn", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Turn submitted...", HttpStatus.OK);
    }

    @Generated
    @GetMapping("/turns")
    public ResponseEntity<List<Turn>> getTurnsByLastRound(){
        List<Turn> turns;
        try{
            turns = trainerService.getAllTurnsOfLastRound();
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(List.of(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(turns, HttpStatus.OK);
    }
}
