package com.revature.services;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.beans.Person;
import com.revature.data.GameHistoryDAO;
import com.revature.data.GameHistoryDAOFactory;

import java.util.List;

public class GameHistoryServiceImpl implements GameHistoryService{
    private GameHistoryDAO gameHistoryDAO;
    public GameHistoryServiceImpl() {
        GameHistoryDAOFactory gameHistoryDAOFactory = new GameHistoryDAOFactory();
        gameHistoryDAO = gameHistoryDAOFactory.getGameHistoryDAO();
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
    public List<GameHistory> getByPersonId(Integer id) {
        return gameHistoryDAO.getByPersonId(id);
    }
}
