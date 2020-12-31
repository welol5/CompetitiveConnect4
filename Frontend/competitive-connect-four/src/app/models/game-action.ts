import { Person } from "./Person";

//this class is used for communicating with the server
export class GameAction {
    message: string; //could also be refered to as a command
    gameID: number;
    row: number; 
    col: number; //col a player dropped a piece in
    player: Person; //Person who did an action

    constructor(){}

    //helpful client-side "constructor" //not actually a constructor, but is should be used immieditatly after
    makeMove(person: Person, gameID: number, row: number, col: number){
        this.row = row;
        this.col = col;
        this.message = 'move';
        this.gameID = gameID;
        this.player = person;
    }
    //helpful client-side "constructors"
    queue(person: Person): void{
        this.player = person;
        this.message = 'queue';
    }

    dequeue(person: Person): void{
        this.player = person;
        this.message = 'dequeue';
    }

}
