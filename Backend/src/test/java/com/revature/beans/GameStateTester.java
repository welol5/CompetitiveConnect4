package com.revature.beans;

import com.revature.exceptions.GameRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTester {

    private static Person player1;
    private static Person player2;

    private static GameState gameState;

    private static String[] p1WinMoveSets = {
            "01010101",
            "11223344",
            "00112233"
    };

    private static String[] p2WinMoveSets = {
            "01020314"
    };

    private static String[] badInput = {
            "0000000000000000",
            "8"
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
        for(String s : p1WinMoveSets){
            gameState.setMoves(s);
            Person p;
            try {
                p = GameState.getWinner(gameState);
                assertEquals(player1, p);
            } catch (GameRuleException e) {

            }
        }
    }

    @Test
    public void testP2Wins(){
        for(String s : p2WinMoveSets){
            gameState.setMoves(s);
            Person p;
            try {
                p = GameState.getWinner(gameState);
                assertEquals(player2, p);
            } catch (GameRuleException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testBadInput(){
        for(String s : badInput){
            gameState.setMoves(s);
            assertThrows(GameRuleException.class, () -> {
                GameState.getWinner(gameState);
            });
        }
    }
}
