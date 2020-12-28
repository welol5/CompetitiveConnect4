import { GameState } from "./game-state";
import { Person } from "./Person";

export class GameHistory {
    id:number;
    timestamp:string;
    winner: Person;
    game: GameState;
}
