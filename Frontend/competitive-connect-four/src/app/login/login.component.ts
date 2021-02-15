import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Output() logInEvent: EventEmitter<any> = new EventEmitter();
  loggedPerson: Person;
  user: string;
  pass: string;

  incorrect: boolean = false;

  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit(): void {
    this.user = '';
    this.pass = '';
    this.logIn();
  }
  ngOnChanges() {

  }
  logIn() {
    if (this.personService.getLoggedPerson()) {
      this.loggedPerson = this.personService.getLoggedPerson();
    } else if (this.user != '' && this.pass != '') {
      this.personService.loginPerson(this.user, this.pass).subscribe(
        (resp) => {
          this.incorrect = false;
          this.loggedPerson = resp;
          this.personService.setLoggedPerson(resp);

        },
        (error) => {
          this.incorrect = true;
        }
      );
    }
  }
  logOut() {
    this.personService.logoutPerson();
    this.loggedPerson = null;
    this.goHome();
  }
  goHome() {
    this.router.navigate(['home']);
  }
}
