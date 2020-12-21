package com.revature.data;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameHistoryDAOImplTest {
    private GameHistoryDAO gameHistoryDAO;
    @Test
    void getByIdtest(){
        GameHistoryDAOFactory gameHistoryDAOFactory = new GameHistoryDAOFactory();
        gameHistoryDAO = gameHistoryDAOFactory.getGameHistoryDAO();
        GameHistory gameHistory = new GameHistory();
        gameHistory = gameHistoryDAO.getByLongId((long) 1);

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
    @Test
    void getByUserIdTest() {
        GameHistoryDAOFactory gameHistoryDAOFactory = new GameHistoryDAOFactory();
        gameHistoryDAO = gameHistoryDAOFactory.getGameHistoryDAO();

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
        List<GameHistory> gameHistoryArrayList = new ArrayList<>();

        g.setTimestamp(gameHistoryDAO.getByLongId(2L).getTimestamp());
        g.setWinner(p);
        g.setId(2L);
        gameHistoryArrayList.add(g);
        assertEquals(gameHistoryArrayList, gameHistoryDAO.getByPersonId(2));

    }
    @Test
    void getLeaderboardTest() {
        GameHistoryDAOFactory gameHistoryDAOFactory = new GameHistoryDAOFactory();
        gameHistoryDAO = gameHistoryDAOFactory.getGameHistoryDAO();

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

        gameState.setId(3L);
        gameState.setMoves("1234567");
        gameState.setPlayer1(p);
        gameState.setPlayer2(p1);
        g.setGame(gameState);
        List<GameHistory> gameHistoryArrayList = new ArrayList<>();

        g.setTimestamp(gameHistoryDAO.getByLongId(3L).getTimestamp());
        g.setWinner(p);
        g.setId(3L);
        gameHistoryArrayList.add(g);
        assertEquals(gameHistoryArrayList, gameHistoryDAO.getDailyLeaderboard());


    }
}
