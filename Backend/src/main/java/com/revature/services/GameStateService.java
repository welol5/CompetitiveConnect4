package com.revature.services;

import com.revature.beans.GameState;
import com.revature.beans.Person;

public interface GameStateService {

	public Long createNewGame(GameState gs); 

	public GameState getGameStateById(Long id);
	
	public void makeMove(GameState gs);
		 
}
