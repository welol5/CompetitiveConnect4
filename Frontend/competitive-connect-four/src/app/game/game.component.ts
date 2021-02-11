
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
  }

  ngOnDestroy(): void {
    this.gameService.closeConnection();
  }

  ngOnInit(): void {

    if(!this.personService.getLoggedPerson()){
      this.router.navigate(["home"]);
    }

    this.gameService.openConnection();
  }

  makeMove(row: number, col: number): void {
    this.gameService.makeMove(-1,row,col);
  }
  dequeue(){
    this.gameService.dequeue();
    this.goHome();
  }
  queue(){
    this.gameService.queueUp();
  }

  playAgain(){
   this.gameService.paired=false;
   this.gameService.winner = null;
   this.queueUp();
  }

  queueUp(){
    this.gameService.queueUp();
  }

  goHome(){
    this.gameService.winner = null;
    this.router.navigate(['home']);
  }

 
}
