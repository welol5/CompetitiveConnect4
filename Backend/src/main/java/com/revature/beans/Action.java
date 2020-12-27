package com.revature.beans;

public class Action {
    private String message;
    private long gameID;
    private Person player;
    private int col;
    private int row;

    public long getGameID() {
        return gameID;
    }

    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getPlayer() {
        return player;
    }

    public void setPlayer(Person player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Action{" +
                "message='" + message + '\'' +
                ", gameID=" + gameID +
                ", playerID=" + player +
                ", col=" + col +
                ", row=" + row +
                '}';
    }
}
