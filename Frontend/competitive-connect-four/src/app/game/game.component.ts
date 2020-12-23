import { Component, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  board : number[][];
  player : number = 1;

  constructor(private gameService: GameService) {

  }

  ngOnInit(): void {
    this.board = [[0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0]];
  }

  makeMove(row: number, col: number): void {
    console.log("(" + row + "," + col + ")");
    // if(this.player == 1){
    //   this.board[row][col] = 1;
    //   this.player = 2;
    // } else {
    //   this.board[row][col] = 2;
    //   this.player = 1;
    // }
    for(let r = this.board.length-1; r >= 0 ; r--){
      if(this.board[r][col] == 1 || this.board[r][col] == 2){
        continue;
      } else {

        //if(move is legal)
        this.board[r][col] = this.player;
        if(this.player == 1){
          this.player = 2;
        } else {
          this.player = 1;
        }
        break;
      }
    }

    // this.gameService.makeMove(row,col).subscribe(
    //   response => {
    //     console.log(response);
    //   }
    // );
  }
}
