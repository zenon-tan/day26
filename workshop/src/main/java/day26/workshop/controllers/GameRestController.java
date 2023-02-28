package day26.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day26.workshop.services.GameService;
import jakarta.json.JsonObject;

@RestController
@RequestMapping
public class GameRestController {

    @Autowired
    GameService gSrc;

    @GetMapping("/games")
    public ResponseEntity<String> getAllGames(@RequestParam(name = "offset", defaultValue = "0") int offset,
    @RequestParam(name = "limit", defaultValue = "25") int limit) {

        JsonObject result = gSrc.getAllGames(limit, offset);

        return new ResponseEntity<>(result.toString(), HttpStatus.OK);


    }

    @GetMapping("/games/rank")
    public ResponseEntity<String> getAllGamesByRank(@RequestParam(name = "offset", defaultValue = "0") int offset,
    @RequestParam(name = "limit", defaultValue = "25") int limit) {

        JsonObject result = gSrc.getAllGamesOrdered(limit, offset);

        return new ResponseEntity<>(result.toString(), HttpStatus.OK);


    }

    @GetMapping("/game/{id}")
    public ResponseEntity<String> getGameById(@PathVariable(name = "id") int id) {

        try {

            JsonObject result = gSrc.getGameByGid(id);

            return new ResponseEntity<>(result.toString(), HttpStatus.OK);
            
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>("gid not found", HttpStatus.NOT_FOUND);
        }
    }

}
