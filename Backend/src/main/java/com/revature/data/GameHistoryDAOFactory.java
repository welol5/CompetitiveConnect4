package com.revature.data;

public class GameHistoryDAOFactory {
    public GameHistoryDAO getGameHistoryDAO() {
        return new GameHistoryDAOImpl();
    }
}
