package com.revature.services;

import com.revature.beans.GameState;
import com.revature.beans.Person;
import com.revature.data.GameStateDAO;
import com.revature.data.GameStateDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class GameStateServiceImpl implements GameStateService {
	private GameStateDAO gameStateDao;
	
	public GameStateServiceImpl() {
		GameStateDAOFactory gameStateDaoFactory = new GameStateDAOFactory();
		gameStateDao = gameStateDaoFactory.getGameStateDAO();
	}

	@Override
	public Long createNewGame(GameState gs) {
		return gameStateDao.add(gs).getId();
	}

	@Override
	public GameState getGateStateById(Long id) {
		return gameStateDao.getById(id);
	}


	@Override
	public void updateGameState(GameState gs) {
		gameStateDao.update(gs);
	}


}
