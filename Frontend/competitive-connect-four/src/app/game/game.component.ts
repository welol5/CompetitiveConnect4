import { Component, OnInit } from '@angular/core';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  loggedPerson: Person;
  constructor(private personService: PersonService) { }

  ngOnInit(): void {
  }

}
