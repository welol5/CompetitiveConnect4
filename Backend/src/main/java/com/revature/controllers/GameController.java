package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.services.GameStateService;
import com.revature.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/game")
public class GameController {
    private GameStateService gameServ;

    @Autowired
    public GameController(GameStateService g){
        gameServ = g;
    }

    @PutMapping
    public ResponseEntity<Person> newGame(HttpSession session, @RequestBody Person player1, @RequestBody Person player2){

        //create socket connections

        //not implemented
        return ResponseEntity.badRequest().build();
    }
}
