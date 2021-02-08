import { Component, OnInit } from '@angular/core';
import { GameHistory } from '../models/game-history';
import { HistoryService } from '../services/history.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { GameService } from '../services/game.service';
import { PersonService } from '../services/person.service';
import { Person } from '../models/Person';
import { AfterViewChecked } from '@angular/core';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit, AfterViewChecked {

  board : number[][];
  private gameHistory : GameHistory;
  private gameHistoryID : number;
  opponent : Person = null;

  boardWidth: number;
  boardHeight: number;

  constructor(private router: Router, private route : ActivatedRoute, private personService: PersonService, private historyService : HistoryService, private gameService : GameService) { }

  ngOnInit(): void {
    if(!this.personService.getLoggedPerson()){
      this.router.navigate(['home']);
    }

    this.board = [[0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0],
              [0,0,0,0,0,0,0]];
    this.route.paramMap.subscribe(params => {
      this.gameHistoryID = parseInt(params.get('id'));
      for(let i = 0; i < this.historyService.getCachedGameHistory().length; i++){
        if(this.historyService.getCachedGameHistory()[i].game.id === this.gameHistoryID){
          this.gameHistory = this.historyService.getCachedGameHistory()[i];
          break;
        }
      }
      if(this.gameHistory.game.player1.id === this.personService.getLoggedPerson().id){
        this.opponent = this.gameHistory.game.player2;
      } else {
        this.opponent = this.gameHistory.game.player1;
      }
      this.board = this.updateBoard();
    });
  }

  private updateBoard() : number[][]{
    let moveList : string = this.gameHistory.game.moves;
    let winner : number = this.gameHistory.winner.id;

    let person : number = this.personService.getLoggedPerson().id;
    let opponent : number = this.opponent.id;


    let turn : number = this.gameHistory.game.player1.id;

    this.gameService.setLoggedPerson();
    this.gameService.emptyBoard();
    for(let i = 0; i < moveList.length; i++){
      this.gameService.makeMove(turn, 0, parseInt(moveList.charAt(i)), false);
      if(turn === person){
        turn = opponent;
      } else {
        turn = person;
      }
    }

    return this.gameService.board;
  }

  ngAfterViewChecked(): void {
    let gameElement : HTMLElement = document.getElementById('game-board') as HTMLElement;
    if(gameElement){
      this.boardWidth = gameElement.offsetWidth;
      this.boardHeight = gameElement.offsetHeight;
    }
  }
}
