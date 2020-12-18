package com.revature.exceptions;

public class GameRuleException extends Exception{

    public GameRuleException(){
        super("Illegal move");
    }

    public GameRuleException(String message){
        super(message);
    }
}
