import { Component, OnDestroy, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit,OnDestroy {

  constructor(public gameService: GameService) {

  }
  ngOnDestroy(): void {
    this.gameService.closeConnection();
  }

  ngOnInit(): void {
    this.gameService.openConnection();
  }

  makeMove(row: number, col: number): void {
    //console.log("click move : " + "(" + row + "," + col + ")");
    this.gameService.makeMove(-1,row,col);
  }

  queue(){
    this.gameService.queueUp();
  }
}
