package com.revature.data;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateDAOImplTest {
    private GameStateDAO gameStateDAO;

    @Test
    void getById() {
        GameStateDAOFactory gameStateDAOFactory = new GameStateDAOFactory();
        gameStateDAO = gameStateDAOFactory.getGameStateDAO();
        GameState g = new GameState();
        g = gameStateDAO.getByLongId(2L);
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
        GameState gameState = new GameState();
        gameState.setId(2L);
        gameState.setMoves("1234567");
        gameState.setPlayer1(p);
        gameState.setPlayer2(p1);

        assertEquals(gameState, g);

    }
}
