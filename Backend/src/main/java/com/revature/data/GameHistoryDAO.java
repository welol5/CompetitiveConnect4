package com.revature.data;

import com.revature.beans.GameHistory;
import com.revature.beans.Person;

import java.util.List;

public interface GameHistoryDAO extends GenericDAO<GameHistory> {

    public List<GameHistory> getDailyLeaderboard();
    public List<GameHistory> getByPersonId(Integer id);

    public GameHistory getByLongId(Long id);

}
