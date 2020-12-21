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
	public GameState getGameStateById(Long id) {
		return gameStateDao.getByLongId(id);
	}


	@Override
	public void makeMove(GameState gs) {
		gameStateDao.update(gs);
	}


}
