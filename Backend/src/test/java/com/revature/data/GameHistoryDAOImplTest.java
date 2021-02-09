package com.revature.data;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@RunWith(SpringRunner.class)
@DataJpaTest
public class GameHistoryDAOImplTest {
    @Autowired
    private GameHistoryDAO gameHistoryDAO;
    @Autowired
    private GameStateDAO gameStateDAO;
    @Autowired
    private PersonDAO personDAO;

    private static Person p1;
    private static Person p2;
    private static GameState game;
    private static LocalDateTime ldt;

    @BeforeAll
    public static void buildHelperObjects(){
        p1 = new Person();
        p1.setPassword("test");
        p1.setUsername("test1");
        p1.setRank(1000);

        p2 = new Person();
        p2.setPassword("test");
        p2.setUsername("test2");
        p2.setRank(1000);

        game = new GameState();
        game.setPlayer1(p1);
        game.setPlayer2(p2);
        game.setMoves("1234567");

        ldt = LocalDateTime.now();
    }

    @BeforeEach
    public void setup(){
        p1.setId(personDAO.save(p1).getId());
        p2.setId(personDAO.save(p2).getId());
        game.setPlayer1(p1);
        game.setPlayer2(p2);
        game.setId(gameStateDAO.save(game).getId());
    }

    @AfterEach
    public void tearDown(){
        personDAO.delete(p1);
        personDAO.delete(p2);
        gameStateDAO.delete(game);
    }

    @Test
    public void getByIdTest(){

        GameHistory gameHistory = new GameHistory();
        gameHistory.setGame(game);
        gameHistory.setWinner(p1);
        gameHistory.setTimestamp(ldt);
        gameHistoryDAO.save(gameHistory);
        gameHistory = gameHistoryDAO.getOne(1L);

        GameHistory g = new GameHistory();
        g.setGame(game);
        g.setTimestamp(ldt);
        g.setWinner(p1);
        g.setId(gameHistory.getId());

        assertEquals(g, gameHistory);
    }

    @Test
    void getByUserIdTest() {
        Person p = personDAO.findByUsername("test1");
        GameHistory gameHistory = new GameHistory();

        gameHistory.setGame(game);
        gameHistory.setWinner(p1);
        gameHistory.setTimestamp(ldt);
        gameHistoryDAO.save(gameHistory);

        assertTrue(gameHistoryDAO.getByPersonId(p.getId()).size() > 0);
    }

    @Test
    void getLeaderboardTest() {
        //make a list of histories
        List<GameHistory> histories = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            GameHistory gh = new GameHistory();
            gh.setGame(game);
            gh.setTimestamp(ldt);
            gh.setWinner(p1);
            histories.add(gh);
        }
        gameHistoryDAO.saveAll(histories);

        List<GameHistory> leaderboard = gameHistoryDAO.getDailyLeaderboard();
        assertTrue(leaderboard.size() > 0);
    }
}
