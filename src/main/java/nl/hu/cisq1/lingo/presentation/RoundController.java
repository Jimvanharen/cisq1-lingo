package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.Generated;
import nl.hu.cisq1.lingo.application.TrainerService;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/round")
public class RoundController {

    private final TrainerService trainerService;
    private final WordService wordService;

    public RoundController(TrainerService trainerService, WordService wordService) {
        this.trainerService = trainerService;
        this.wordService = wordService;
    }

    @PostMapping("/start-round/{id}")
    public ResponseEntity<?> startRoundById(@PathVariable Long id){
        try{
            trainerService.startRound(trainerService.getGameById(id), wordService.provideRandomWordByRandomLength());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to start round", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Started round on game with id: " + id, HttpStatus.OK);
    }
    @PostMapping("/start-round")
    public ResponseEntity<?> startRound(){
        try{
            trainerService.startRound(trainerService.getGameById(trainerService.getMaxId()), wordService.provideRandomWordByRandomLength());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to start round", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Started round on game with id: " + trainerService.getMaxId(), HttpStatus.OK);
    }

    @Generated
    @DeleteMapping("delete-round/{id}")
    public ResponseEntity<?> removeRound(@PathVariable Long id){
        try{
            trainerService.removeRound(id);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong removing the round", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Succesfully deleted round with id: " + id, HttpStatus.OK);
    }


}
