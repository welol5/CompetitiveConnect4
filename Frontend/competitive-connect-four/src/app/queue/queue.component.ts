import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-queue',
  templateUrl: './queue.component.html',
  styleUrls: ['./queue.component.css']
})
export class QueueComponent implements OnInit {
  loggedPerson: Person;

  constructor(private personService: PersonService) { }
  
  ngOnInit(): void {
    /*console.log(this.loggedPerson);
    this.personService.loginPerson(null,null).subscribe(
      resp => {
        this.loggedPerson = resp;
      }
    );*/
    this.loggedPerson = this.personService.getLoggedPerson();
    console.log("queue button: ");
    console.log(this.loggedPerson);
  }

  printPerson(){
      this.ngOnInit();
  }

}
