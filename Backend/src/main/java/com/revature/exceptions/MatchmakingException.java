package com.revature.exceptions;

public class MatchmakingException extends Exception{

    public MatchmakingException(){
        super("Cannot add player to matchmaking queue");
    }

    public MatchmakingException(String message){
        super(message);
    }
}
