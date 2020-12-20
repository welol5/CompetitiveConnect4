package com.revature.data;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameHistoryDAOImplTest {
    private GameHistoryDAO gameHistoryDAO;
    @Test
    void getByIdtest(){
        GameHistoryDAOFactory gameHistoryDAOFactory = new GameHistoryDAOFactory();
        gameHistoryDAO = gameHistoryDAOFactory.getGameHistoryDAO();
        GameHistory gameHistory = new GameHistory();
        gameHistory = gameHistoryDAO.getById(1);

        GameHistory g = new GameHistory();
        GameState gameState = new GameState();
        Person p = new Person();
        p.setId(2);
        p.setPassword("test");
        p.setUsername("test");
        p.setRank(1000);
        Person p1 = new Person();
        p1.setId(1);
        p1.setPassword("test");
        p1.setUsername("test2");
        p1.setRank(1000);

        gameState.setId(2L);
        gameState.setMoves("1234567");
        gameState.setPlayer1(p);
        gameState.setPlayer2(p1);
        g.setGame(gameState);

        assertEquals(g, gameHistory);
    }
}
