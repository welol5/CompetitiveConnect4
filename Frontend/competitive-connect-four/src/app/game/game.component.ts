
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})

export class GameComponent implements OnInit,OnDestroy {
  loggedPerson: Person;
  constructor(public gameService: GameService,private personService: PersonService, private router: Router) {

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
    this.router.navigate(['home']);
  }
  queue(){
    this.gameService.queueUp();
  }
}
