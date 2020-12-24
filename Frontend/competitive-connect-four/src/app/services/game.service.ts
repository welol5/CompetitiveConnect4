import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { GameAction } from '../models/game-action';
import { SocketioService } from './socketio.service';

@Injectable({
  providedIn: 'root'
})
export class GameService implements OnDestroy{

  gameID: number;

  constructor(private http: HttpClient,private socketService: SocketioService) {
    console.log('construct');
    this.socketService.openConnection();
  }

  ngOnDestroy(): void {
    this.socketService.closeConnection();
  }

  makeMove(gameID: number, row: number, col: number){
    let gameAction: GameAction = new GameAction();
    gameAction.makeMove(gameID,row,col);
    this.socketService.sendMessage(gameAction);
  }
}
