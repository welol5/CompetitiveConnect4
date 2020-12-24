import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  loggedPerson: Person;
  user: string;
  pass: string;

  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit(): void {
    this.user = '';
    this.pass = '';
  }
  register() {
    this.personService.registerPerson(this.user, this.pass).subscribe(
      resp => {
        this.loggedPerson = resp;
        this.router.navigate(['home']);
      }
    )
  }

}
