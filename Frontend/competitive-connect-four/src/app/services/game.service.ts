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
  private person: Person;

  board: number[][];
  private isTurn: boolean;

  private webSocket : WebSocket;
  private url : string;

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.url = 'ws://localhost:8080/CompetitiveConnect4_war_exploded/gameaction';
    this.emptyBoard();

    this.person = new Person();
    this.person.id = Math.floor(Math.random() * 1000);
    this.person.username = 'queueTester';
    this.person.rank = 1000;//Math.floor(Math.random() * 1000);
  }

  sendMove(gameID: number, row: number, col: number){
    let gameAction: GameAction = new GameAction();
    gameAction.makeMove(this.person,gameID,row,col);
    this.sendMessage(gameAction);
    //this.makeMove(this.person.id,row,col);
  }

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
        if(player == this.person.id){
          this.board[r][col] = 1;
          this.sendMove(this.gameID,row,col);
          this.isTurn = false;
        } else {
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

    this.webSocket.onmessage = (event) => {
      //recive message
      const action : GameAction = JSON.parse(event.data);
      console.log(action);
      
      if(action.message == 'queued'){
        //console.log('queued up');
      } else if(action.message == 'move'){
        //console.log('move', action);
        let row: number = action.row;
        let col: number = action.col;
        let playerNumber : number = 2;
        this.makeMove(playerNumber,row,col);
      } else if(action.message == 'go'){
        this.gameID = action.gameID;
        this.isTurn = true;
      } else if(action.message == 'wait'){
        this.gameID = action.gameID;
        this.isTurn = false;
      } else if(action.message == 'win'){
        this.isTurn = false;
      } else if(action.message == 'lose'){
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
