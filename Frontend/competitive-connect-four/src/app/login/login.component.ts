import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  user: string;
  pass: string;
  loggedPerson: Person;

  incorrect: boolean = false;
  isRegistering : boolean = false;

  constructor(private personService: PersonService) {}

  ngOnInit(): void {
    this.user = '';
    this.pass = '';
    this.logIn();
  }
  ngOnChanges() {

  }

  logIn() {
    if (this.personService.getLoggedPerson()) {
      console.log('no logged user');
    } else if (this.user != '' && this.pass != '') {
      console.log('person found');
      this.personService.loginPerson(this.user, this.pass).subscribe(
        (resp) => {
          console.log('called subscribe');
          this.incorrect = false;
          this.personService.setLoggedPerson(resp);
          this.loggedPerson = resp;
        },
        (error) => {
          this.incorrect = true;
        }
      );
    }
  }

  logOut() {
    this.personService.logoutPerson();
  }

  getLoggedPerson(){
    console.log(this.loggedPerson);
    return this.loggedPerson;
  }
}
