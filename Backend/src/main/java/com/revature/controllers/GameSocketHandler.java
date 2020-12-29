package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.*;
import com.revature.services.GameHistoryService;
import com.revature.services.GameHistoryServiceImpl;
import com.revature.services.GameStateService;
import com.revature.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

public class GameSocketHandler extends TextWebSocketHandler {
    private @Autowired GameHistoryService gameHistoryServ;
    private @Autowired PersonService personService;
    private final Map<Long, ActiveGame> activeGames = new HashMap<>();
    private final List<QueuePosition> queue = new ArrayList<>();
    private final QueuePosition.RankComparitor comparitor = new QueuePosition.RankComparitor();
    private ObjectMapper objectMapper = new ObjectMapper();

    private @Autowired GameStateService gameStateService;
//    @Autowired
//    public GameSocketHandler(GameHistoryService g, GameStateService gs, PersonService p) {
//        gameHistoryServ = g;
//        gameStateService = gs;
//        personService = p;
//    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //System.out.println("Connection made");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //System.out.println(message.getPayload());
        Action action = objectMapper.readValue(message.getPayload(), Action.class);

        if(action.getMessage().equals("queue")){

            boolean foundGame = false;
            for(QueuePosition qp: queue){
                if(Math.abs(qp.getPlayer().getRank()-action.getPlayer().getRank()) < 100){
                    System.out.println("Found game");
                    //remove other player from the queue
                    queue.remove(qp);

                    foundGame = true;
                    //Found a valid game, creating a new game
                    GameState newGame = new GameState();
                    newGame.setMoves("");
                    newGame.setPlayer1(action.getPlayer());
                    newGame.setPlayer2(qp.getPlayer());
                    //gameStateService.createNewGame(newGame);
                    //new game created

                    ActiveGame newActiveGame = new ActiveGame(session, qp.getPlayerSession(), newGame);
                    activeGames.put(newGame.getId(), newActiveGame);

                    //tell player 1 to make a move
                    Action player1StartAction = new Action();
                    player1StartAction.setMessage("go");
                    TextMessage p1Command = new TextMessage(objectMapper.writeValueAsString(player1StartAction));
                    session.sendMessage(p1Command);

                    //tell player 2 to wait
                    Action player2WaitAction = new Action();
                    player2WaitAction.setMessage("wait");
                    TextMessage p2Command = new TextMessage(objectMapper.writeValueAsString(player2WaitAction));
                    qp.getPlayerSession().sendMessage(p2Command);
                    break;
                }
            }

            if(!foundGame) {
                System.out.println("added player to queue with rank " + action.getPlayer().getRank());
                queue.add(new QueuePosition(action.getPlayer(), session));
                Collections.sort(queue, comparitor);
            }
        } else if(action.getMessage().equals("move")) {
            long gameID = action.getGameID();
            ActiveGame game = activeGames.get(gameID);
            boolean valid = game.getGameState().makeMove(action.getCol());
            if(valid){
                Action response = new Action();
                response.setPlayer(action.getPlayer());
                response.setCol(action.getCol());
                response.setRow(action.getRow());
                response.setGameID(action.getGameID());

                //get the other players session
                WebSocketSession otherPlayerSession;
                if(session == game.getPlayer1Session()){
                    otherPlayerSession = game.getPlayer2Session();
                } else {
                    otherPlayerSession = game.getPlayer1Session();
                }
                System.out.println(session);
                System.out.println(otherPlayerSession);
                if(GameState.getWinner(game.getGameState()) == null){

                    //respond to player who made the move
                    response.setMessage("ok");
                    TextMessage moveResponseTextMessage = new TextMessage(objectMapper.writeValueAsString(response));
                    session.sendMessage(moveResponseTextMessage);

                    //let the other player know a move has been made
                    response.setMessage("move");
                    moveResponseTextMessage = new TextMessage(objectMapper.writeValueAsString(response));
                    otherPlayerSession.sendMessage(moveResponseTextMessage);
                } else {
                    //there is a winner
                    //let the winner know
                    response.setMessage("win");
                    TextMessage moveResponseTextMessage = new TextMessage(objectMapper.writeValueAsString(response));
                    session.sendMessage(moveResponseTextMessage);
                    //build game history for DB here
                    GameState currentGame = game.getGameState();
                    long gameId = gameStateService.createNewGame(currentGame);
                    currentGame.setId(gameId);
                    GameHistory gameHistory = gameHistoryServ.newGameHistory(action.getPlayer(), currentGame);
                    //calculate points gained or lost
                    Person loser;
                    if (action.getPlayer() == currentGame.getPlayer1()) {
                        loser = currentGame.getPlayer2();
                    } else loser = currentGame.getPlayer1();
                    personService.calculatePoints(action.getPlayer(), loser);

                    //let the other player know a move has been made and they lost
                    response.setMessage("lose");
                    moveResponseTextMessage = new TextMessage(objectMapper.writeValueAsString(response));
                    otherPlayerSession.sendMessage(moveResponseTextMessage);
                }
            } else {
                //idk crash and burn
            }
        }


        //echo action
//        String echoText
//            = objectMapper.writeValueAsString(action);
//        TextMessage echoMessage = new TextMessage(echoText);
//        session.sendMessage(echoMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session,status);
    }

    private void addPlayerToQueue(WebSocketSession session, int playerID){

    }
}
