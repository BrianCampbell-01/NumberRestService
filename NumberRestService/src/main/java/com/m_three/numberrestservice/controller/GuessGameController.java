/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m_three.numberrestservice.controller;

import com.m_three.numberrestservice.dao.GuessGameDao;
import com.m_three.numberrestservice.dto.Game;
import com.m_three.numberrestservice.dto.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Brian
 */
@RestController
@RequestMapping("/api/game")
public class GuessGameController {

    private final GuessGameDao dao;

    public GuessGameController(GuessGameDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Game> all() {
        return dao.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable int id) {
        Game result = dao.getGameByID(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody Game game) {
        return dao.addGame(game);
    }
    
    @Transactional
    @PostMapping("/play")
    @ResponseStatus(HttpStatus.CREATED)
    public Round playGame(@RequestBody Game game) {
        Game result = dao.getGameByID(game.getGameID());
        Round round = new Round();
        String guessNumberString = String.valueOf(game.getGuessNumber());

        String gameNumberString = String.valueOf(result.getGameNumber());
        int partialCount = 0, exactMatch = 0;
        for (int i = 0; i < 4; i++) {

            if (guessNumberString.charAt(i) == (gameNumberString.charAt(i))) {
                exactMatch++;
            } else {
                if (gameNumberString.contains(String.valueOf(guessNumberString.charAt(i)))) {
                    partialCount++;
                }
            }

        }
        if (exactMatch == 4) {
            game.setGameStatus("Finished");
            dao.updateGame(game);
        }
        round.setRoundDateTime(LocalDateTime.now().toString());
        round.setGameID(game.getGameID());
        String guessAnswer = "e:" + exactMatch + "p:" + partialCount;
        round.setGuess(guessAnswer);

        dao.createRound(round);

        return round;
    }

    @GetMapping("/round/{id}")
    public List<Round> findRoundByGameID(@PathVariable int id) {
       //List<Round> result = dao.getRoundByGameID(id);
//        if (result == null) {
//            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
//        }
        return dao.getRoundByGameID(id);
    }
}
/**
 * private int roundID; private LocalDateTime roundDateTime; private int gameID;
 * private String guess;
 */
