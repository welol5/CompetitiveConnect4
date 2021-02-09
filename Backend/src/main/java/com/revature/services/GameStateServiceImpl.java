package com.revature.services;

import com.revature.beans.GameState;
import com.revature.data.GameStateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStateServiceImpl implements GameStateService {
	private GameStateDAO gameStateDao;

	@Autowired
    public GameStateServiceImpl(GameStateDAO gs) {
        gameStateDao = gs;
    }

	@Override
	public Long createNewGame(GameState gs) {
		return gameStateDao.save(gs).getId();
	}

	@Override
	public GameState getGameStateById(Long id) {
		return gameStateDao.getOne(id);
	}


	@Override
	public void makeMove(GameState gs) {
		gameStateDao.save(gs);
	}


}
