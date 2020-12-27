import { Person } from "./Person";

export class GameAction {
    message: string;
    gameID: number;
    row: number;
    col: number;
    player: Person;

    constructor(){}

    makeMove(person: Person, gameID: number, row: number, col: number){
        this.row = row;
        this.col = col;
        this.message = 'move';
        this.gameID = gameID;
    }

    queue(person: Person): void{
        this.player = person;
        this.message = 'queue';
    }

}
