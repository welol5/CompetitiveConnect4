package com.revature.beans;

import java.util.Objects;

public class GameState {
    private long id; //Who knows how many games people could play
    private String moves;
    private User player1;
    private User player2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return id == gameState.id && Objects.equals(moves, gameState.moves) && Objects.equals(player1, gameState.player1) && Objects.equals(player2, gameState.player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moves, player1, player2);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "id=" + id +
                ", moves='" + moves + '\'' +
                ", player1=" + player1 +
                ", player2=" + player2 +
                '}';
    }

    /**
     *
     * @param gs The games state that a winner will be searched for
     * @return The winning player or null if there is no winner
     */
    public static User getWinner(GameState gs){

        //build the game board as an array
        int[][] board = generateGameBoard(gs.getMoves());

        //check horizontals
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length-3; col++){
                int player = board[row][col];
                if(player == 0){
                    continue;
                } else {
                    if(player == board[row][col+2]){
                        if(player == board[row][col+3]){
                            if(player == board[row][col+4]){
                                if(player == 1){
                                    return gs.getPlayer1();
                                } else {
                                    return gs.getPlayer2();
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }

        //check verticals
        for(int row = 0; row < board.length-3; row++){
            for(int col = 0; col < board[row].length; col++){
                int player = board[row][col];
                if(player == 0){
                    continue;
                } else {
                    if(player == board[row+1][col]){
                        if(player == board[row+2][col]){
                            if(player == board[row+3][col]){
                                if(player == 1){
                                    return gs.getPlayer1();
                                } else {
                                    return gs.getPlayer2();
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }

        //check diagonals
        for(int row = 0; row < board.length-3; row++){
            for(int col = 0; col < board[row].length-3; col++){
                int player = board[row][col];
                if(player == 0){
                    continue;
                } else {
                    if(player == board[row+1][col+1]){
                        if(player == board[row+2][col+2]){
                            if(player == board[row+3][col+3]){
                                if(player == 1){
                                    return gs.getPlayer1();
                                } else {
                                    return gs.getPlayer2();
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }

        //check reverse diagonals
        for(int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 3; col++) {
                int player = board[row][col];
                if (player == 0) {
                    continue;
                } else {
                    if (player == board[row - 1][col + 1]) {
                        if (player == board[row - 2][col + 2]) {
                            if (player == board[row - 3][col + 3]) {
                                if (player == 1) {
                                    return gs.getPlayer1();
                                } else {
                                    return gs.getPlayer2();
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }

    private static int[][] generateGameBoard(String moveList){
        //int[row][col]
        //board[0][0] is the bottom left
        int[][] board = new int[6][7];

        boolean isPlayer1 = true;
        for(char c : moveList.toCharArray()){
            int col = Integer.valueOf("" + c);
            int row = 0;
            for(int i = 0; board[i][col] != 0 ; i++) {
                row = i;
            }

            if(isPlayer1){
                board[row][col] = 1;
            } else {
                board[row][col] = 2;
            }
        }

        return board;
    }
}
