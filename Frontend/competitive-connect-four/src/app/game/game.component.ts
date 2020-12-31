
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';
import { AfterViewChecked, Component, OnDestroy, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})

export class GameComponent implements OnInit,OnDestroy,AfterViewChecked {
  loggedPerson: Person;
  boardWidth: number;
  boardHeight: number;
  constructor(public gameService: GameService,private personService: PersonService, private router: Router) {
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
  dequeue(){
    //Dequeue code
    this.gameService.dequeue();
    this.goHome();
  }
  queue(){
    this.gameService.queueUp();
  }

  playAgain(){
  //console.log('play again', this.personService.getLoggedPerson());
    
   this.gameService.paired=false;
   this.gameService.winner = null;
  // this.personService.refreshPerson();
   //console.log(this.personService.getLoggedPerson());
   this.queueUp();
  }

  queueUp(){
    this.gameService.queueUp();
  }

  goHome(){
   // console.log('home',this.personService.getLoggedPerson());
    this.gameService.winner = null;
   // this.personService.refreshPerson();
    this.router.navigate(['home']);
  }

 
}
