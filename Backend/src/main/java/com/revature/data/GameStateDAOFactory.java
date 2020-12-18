package com.revature.data;

public class GameStateDAOFactory {
    public  GameStateDAO getGameStateDAO() {
        return new GameStateDAOImpl();
    }
}
