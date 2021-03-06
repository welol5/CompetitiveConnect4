package com.revature.data;

import com.revature.beans.GameState;

public interface GameStateDAO extends GenericDAO<GameState> {
    public GameState getByLongId(Long id);
}
