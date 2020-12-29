package com.revature.controllers;

import com.revature.beans.Action;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import com.revature.exceptions.MatchmakingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameSocketHandlerTester {

    private static GameSocketHandler gsh = new GameSocketHandler();

    private static Person player1;
    private static Person player2;

    @BeforeAll
    public static void beforeAll(){
        player1 = new Person();
        player1.setUsername("test");
        player1.setPassword("test");
        player1.setId(2);

        player2 = new Person();
        player2.setUsername("test2");
        player2.setPassword("test");
        player2.setId(1);
    }

    @Test
    @Order(1)
    public void testQueue(){
        //setup
        Action queueAction = new Action();
        queueAction.setMessage("queue");
        queueAction.setPlayer(player1);

        //more setup
        Action queueAction2 = new Action();
        queueAction2.setMessage("queue");
        queueAction2.setPlayer(player2);

        try {
            gsh.addPlayerToQueue(null,player1);
        } catch (Exception e){
            e.printStackTrace();
        }

        assertThrows(MatchmakingException.class, () -> {
            gsh.addPlayerToQueue(null, player1);
        });

        gsh.dequeue(player1);

        try {
            gsh.addPlayerToQueue(null,player1);
        } catch (Exception e){
            e.printStackTrace();
        }

        //sessions are null, it should find a game ans throw a null pointer when trying to start the game
        assertThrows(NullPointerException.class, () -> {
            gsh.addPlayerToQueue(null, player2);
        });
    }

}
