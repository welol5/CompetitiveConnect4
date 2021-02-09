package com.revature.data;

import com.revature.beans.GameHistory;
import com.revature.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameHistoryDAO extends JpaRepository<GameHistory, Long> {

    @Query("select gh from GameHistory gh where gh.timestamp >= current_date()")
    public List<GameHistory> getDailyLeaderboard();
    @Query("select gh from GameHistory gh where gh.game.player1.id = :id or gh.game.player2.id = :id")
    public List<GameHistory> getByPersonId(Integer id);

}
