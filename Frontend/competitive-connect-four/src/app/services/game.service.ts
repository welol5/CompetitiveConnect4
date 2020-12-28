import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { GameAction } from '../models/game-action';
import { Person } from '../models/Person';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class GameService{

  gameID: number;
  //the current player
  private person: Person;

  board: number[][];
  //true if it is this players turn
  private isTurn: boolean;

  private webSocket : WebSocket;
  //socket url, this is slightly different from the http one
  private url : string;

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = 'ws://localhost:8080/CompetitiveConnect4_war_exploded/gameaction';
    this.emptyBoard();

    this.person = new Person();
    this.person.id = Math.floor(Math.random() * 1000);
    this.person.username = 'queueTester';
    this.person.rank = 1000;//Math.floor(Math.random() * 1000);
  }

  //uses the websocket to send a game move
  sendMove(gameID: number, row: number, col: number){
    let gameAction: GameAction = new GameAction();
    gameAction.makeMove(this.person,gameID,row,col);
    this.sendMessage(gameAction);
    //this.makeMove(this.person.id,row,col);
  }

  //uses the websocket to tell the server you want to enter the matchmaking queue
  queueUp(): void {
    console.log('queue');
    let queueAction: GameAction = new GameAction();
    queueAction.queue(this.person);
    this.sendMessage(queueAction);
  }

  emptyBoard(): void {
    this.board = [[0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0]];
  }

  //called when either player makes a move to update the board
  makeMove(player: number, row: number, col: number): void {
    if(player == -1){
      player = this.person.id;
      if(!this.isTurn){
        return;
      }
    }
    //console.log("Making move :" + "(" + row + "," + col + ")");
    for(let r = this.board.length-1; r >= 0 ; r--){
      if(this.board[r][col] == 1 || this.board[r][col] == 2){
        continue;
      } else {

        //if(move is legal)
        //do nothing if the move was not legal
        if(player == this.person.id){
          //if the player is the local one, send the move to the server
          this.board[r][col] = 1;
          this.sendMove(this.gameID,row,col);
          this.isTurn = false;
        } else {
          //the other player made a move, just reflect it on the board
          this.board[r][col] = 2;
          this.isTurn = true;
        }
        break;
      }
    }
  }

  //socket stuff////////////////////////////////////////////////////////////////
  openConnection(){
    this.webSocket = new WebSocket(this.url);

    this.webSocket.onopen = (event) => {
      //console.log('Open: ', event);
    };

    //this runs any time a message is recivced from the server
    this.webSocket.onmessage = (event) => {
      //recive message
      const action : GameAction = JSON.parse(event.data);
      console.log(action);
      
      if(action.message == 'queued'){
        //no longer uses the 'queued' message
        //console.log('queued up');
      } else if(action.message == 'move'){
        //called whenever the other player made a move
        //console.log('move', action);
        let row: number = action.row;
        let col: number = action.col;
        let playerNumber : number = 2;
        this.makeMove(playerNumber,row,col);
      } else if(action.message == 'go'){
        //called at the start of the game if this is player 1
        this.gameID = action.gameID;
        this.isTurn = true;
      } else if(action.message == 'wait'){
        //called at the start of the game if this is player 2
        this.gameID = action.gameID;
        this.isTurn = false;
      } else if(action.message == 'win'){
        //called if this player won the game
        this.isTurn = false;
      } else if(action.message == 'lose'){
        //called if this player lost the game
        this.isTurn = false;
      }
    };

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event);
    };
  }

  private sendMessage(gameAction: GameAction){
    //console.log(gameAction);
    this.webSocket.send(JSON.stringify(gameAction));
  }

  closeConnection(){
    this.webSocket.close();
  }
}
