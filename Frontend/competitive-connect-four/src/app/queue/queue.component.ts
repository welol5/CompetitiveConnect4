import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-queue',
  templateUrl: './queue.component.html',
  styleUrls: ['./queue.component.css']
})
export class QueueComponent implements OnInit {
  loggedPerson: Person;
  error:String;
  constructor(private personService: PersonService, private router: Router, public gameService: GameService) { }
  
  ngOnInit(): void {
    this.error="";
    this.loggedPerson = this.personService.getLoggedPerson();
    console.log("queue button: ");
    console.log(this.loggedPerson);
  }
  queue(){
    this.ngOnInit();
    if(this.loggedPerson){
      this.router.navigate(['game']);
    }else{
      this.error="You must be logged in to queue";
    }
  }
  printPerson(){
      this.ngOnInit();
  }

}

