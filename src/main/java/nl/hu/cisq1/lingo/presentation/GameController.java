package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.application.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/start-game")
    public ResponseEntity<?> startGame(){
        try {
            gameService.startGame();
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Game failed to create", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Game Created", HttpStatus.OK);
    }
}