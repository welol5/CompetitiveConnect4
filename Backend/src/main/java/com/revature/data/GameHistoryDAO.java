package com.revature.data;

import com.revature.beans.GameHistory;

public interface GameHistoryDAO extends GenericDAO<GameHistory> {
	public GameHistory getByLongId(Long id);
}
