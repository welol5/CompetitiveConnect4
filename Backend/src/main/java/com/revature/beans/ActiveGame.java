package com.revature.beans;

import org.springframework.web.socket.WebSocketSession;

public class ActiveGame {
    private WebSocketSession player1Session;
    private WebSocketSession player2Session;
    private GameState gameState;

    public ActiveGame(WebSocketSession player1Session, WebSocketSession player2Session, GameState gameState) {
        this.player1Session = player1Session;
        this.player2Session = player2Session;
        this.gameState = gameState;
    }

    public WebSocketSession getPlayer1Session() {
        return player1Session;
    }

    public WebSocketSession getPlayer2Session() {
        return player2Session;
    }

    public GameState getGameState() {
        return gameState;
    }
}
