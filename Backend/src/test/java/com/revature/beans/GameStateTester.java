package com.revature.beans;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GameStateTester {

    private static User player1;
    private static User player2;

    private static GameState gameState;

    private static String[] p1WinMoveSets = {
            "01010101",
            "11223344"
    };

    private static String[] badInput = {
            "111111111111111111"
    };

    @BeforeAll
    public static void beforeAll(){
        player1 = new User();
        player1.setUsername("p1");
        player1.setPassword("password");

        player2 = new User();
        player2.setUsername("p2");
        player2.setPassword("password");

        gameState = new GameState();
        gameState.setPlayer1(player1);
        gameState.setPlayer2(player2);
    }

    @Test
    public void testP1Wins(){
        for(String s : p1WinMoveSets){
            gameState.setMoves(s);
            assertEquals(player1, GameState.getWinner(gameState));
        }
    }

    @Test
    public void testBadInput(){
        for(String s : badInput){
            gameState.setMoves(s);
            assertNull(GameState.getWinner(gameState));
        }
    }
}
