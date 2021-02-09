package com.revature.data;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameStateDAOImplTest {
    @Autowired
    private GameStateDAO gameStateDAO;
    @Autowired
    private PersonDAO personDAO;



    @Test
    public void getById() {
        Person p1 = new Person();
        p1.setPassword("test");
        p1.setUsername("test1");
        p1.setRank(1000);
        personDAO.save(p1);
        Person p2 = new Person();
        p2.setPassword("test");
        p2.setUsername("test2");
        p2.setRank(1000);
        personDAO.save(p2);

        GameState g = new GameState();
        g.setMoves("1234567");
        g.setPlayer1(p1);
        g.setPlayer2(p2);

        gameStateDAO.save(g);
        g = gameStateDAO.getOne(1L);

        GameState gameState = new GameState();
        gameState.setId(1L);
        gameState.setMoves("1234567");
        gameState.setPlayer1(p1);
        gameState.setPlayer2(p2);

        assertEquals(gameState, g);

    }
}
