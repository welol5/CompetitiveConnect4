package com.revature.services;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import com.revature.data.GameHistoryDAO;
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
        return gameHistoryDAO.save(g);
    }



    @Override
    public GameHistory getById(Long id) {
        return gameHistoryDAO.findById(id).get();
    }

    @Override
    public List<GameHistory> getDailyLeaderboard() {
        return gameHistoryDAO.getDailyLeaderboard();
    }

    @Override
    public List<GameHistory> getByPersonId(Integer id) {
        return gameHistoryDAO.getByPersonId(id);
    }
}
