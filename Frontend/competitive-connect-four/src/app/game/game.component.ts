
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';
import { AfterViewChecked, Component, OnDestroy, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})

export class GameComponent implements OnInit,OnDestroy,AfterViewChecked {
  loggedPerson: Person;
  boardWidth: number;
  boardHeight: number;
  constructor(public gameService: GameService,private personService: PersonService) {

  }
  ngAfterViewChecked(): void {
    let gameElement : HTMLElement = document.getElementById('game-board') as HTMLElement;
    this.boardWidth = gameElement.offsetWidth;
    this.boardHeight = gameElement.offsetHeight;
    //console.log('width', this.boardWidth);
    //console.log('height', this.boardHeight);
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

  //this is here till an actual queue button is ready
  queue(){
    this.gameService.queueUp();
  }

  playAgain(){
    console.log('play again');
  }

  goHome(){
    console.log('home');
  }
}
