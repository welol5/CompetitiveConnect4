package com.revature.services;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import com.revature.data.GameHistoryDAO;
import com.revature.data.GameHistoryDAOFactory;
import com.revature.data.GameStateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameHistoryServiceImpl implements GameHistoryService{
    private GameHistoryDAO gameHistoryDAO;
    @Autowired
    public GameHistoryServiceImpl(GameHistoryDAO g) {
        gameHistoryDAO = g;
    }
    @Override
    public GameHistory newGameHistory(Person winner, GameState game) {
        GameHistory g = new GameHistory();
        g.setGame(game);
        g.setWinner(winner);
        return gameHistoryDAO.add(g);
    }



    @Override
    public GameHistory getById(Long id) {
        return gameHistoryDAO.getByLongId(id);
    }

    @Override
    public List<GameHistory> getDailyLeaderboard() {
        return gameHistoryDAO.getDailyLeaderboard();
    }

    @Override
    public List<GameHistory> getByPersonId(Long id) {
        return gameHistoryDAO.getByPersonId(id);
    }
}
