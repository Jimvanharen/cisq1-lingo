package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.application.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final TrainerService trainerService;

    public GameController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @PostMapping("/start-game")
    public ResponseEntity<?> startGame(){
        try {
            trainerService.startGame();
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Game failed to create", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Game Created", HttpStatus.OK);
    }
}
