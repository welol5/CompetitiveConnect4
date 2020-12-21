package com.revature.services;

import com.revature.beans.GameState;
import com.revature.beans.Person;

public interface GameStateService {

	public Long createNewGame(GameState gs); 

	public GameState getGateStateById(Long id);
	
	public void updateGameState(GameState gs);
		 
}
