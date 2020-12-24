package com.revature.controllers;

import com.revature.beans.Action;
import com.revature.beans.ActiveGame;
import com.revature.beans.GameState;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

public class GameSocketHandler extends TextWebSocketHandler {

    private final Map<Long, ActiveGame> activeGames = new HashMap<>();
    //private final Map<Integer, > matchmakingQueue = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection made");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.toString());
        Action action = parseAction(message.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session,status);
    }

    private Action parseAction(String actionString){
        Action action = new Action();
        return action;
    }
}
