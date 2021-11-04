package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.application.GameService;
import nl.hu.cisq1.lingo.application.RoundService;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/round")
public class RoundController {

    private RoundService roundService;
    private WordService wordService;
    private GameService gameService;

    public RoundController(RoundService roundService, WordService wordService, GameService gameService){
        this.roundService = roundService;
        this.wordService = wordService;
        this.gameService = gameService;
    }

    @PostMapping("/start-round/{id}")
    public ResponseEntity<?> startRoundById(@PathVariable Long id){
        try{
            roundService.startRound(gameService.getGameById(id), wordService.provideRandomWordByRandomLength());
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
            roundService.startRound(gameService.getGameById(gameService.getMaxId()), wordService.provideRandomWordByRandomLength());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to start round", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Started round on game with id: " + gameService.getMaxId(), HttpStatus.OK);
    }

    @DeleteMapping("delete-round/{id}")
    public ResponseEntity<?> removeRound(@PathVariable Long id){
        try{
            roundService.removeRound(id);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong removing the round", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Succesfully deleted round with id: " + id, HttpStatus.OK);
    }


}
