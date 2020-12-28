import { Component, OnInit } from '@angular/core';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  loggedPerson: Person;
  constructor(private personService: PersonService) { }
  
  ngOnInit(): void {
  }

}
