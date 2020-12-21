package com.revature.services;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.beans.Person;

import java.util.List;

public interface GameHistoryService {
    public GameHistory newGameHistory(Person winner, GameState game);
    public GameHistory getById(Integer id);
    public List<GameHistory> getDailyLeaderboard();
    public List<GameHistory> getByPersonId(Integer id);
}
