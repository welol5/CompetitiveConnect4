export class GameAction {
    private message: string;
    private gameID: number;
    private row: number;
    private col: number;

    constructor(){}

    makeMove(gameID: number, row: number, col: number){
        this.row = row;
        this.col = col;
        this.message = 'move';
        this.gameID = gameID;
    }

    getMoveCol(){
        return this.col;
    }

    getMessage(){
        return this.message;
    }
}
