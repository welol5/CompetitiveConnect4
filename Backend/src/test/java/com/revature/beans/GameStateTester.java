package com.revature.beans;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStateTester {

    private static Person player1;
    private static Person player2;

    private static GameState gameState;

    private static String[] moveSets = {
            "12121212",
            "11223344",
            //"122334344"
    };

    @BeforeAll
    public static void beforeAll(){
        player1 = new Person();
        player1.setUsername("p1");
        player1.setPassword("password");

        player2 = new Person();
        player2.setUsername("p2");
        player2.setPassword("password");

        gameState = new GameState();
        gameState.setPlayer1(player1);
        gameState.setPlayer2(player2);
    }

    @Test
    public void testP1Wins(){
        for(String s : moveSets){
            gameState.setMoves(s);
            System.out.println(gameState.getBoardASCIIPicture());
            assertEquals(player1, GameState.getWinner(gameState));
        }
    }
}
