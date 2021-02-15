import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router, RouterEvent } from '@angular/router';
import { filter } from 'rxjs/operators';
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

  incorrect: boolean = false;
  isRegistering : boolean = false;

  constructor(private personService: PersonService, private router: Router) {}

  ngOnInit(): void {
    this.user = '';
    this.pass = '';
    this.logIn();
  }
  ngOnChanges() {

  }
  logIn() {
    if (this.personService.getLoggedPerson()) {
    } else if (this.user != '' && this.pass != '') {
      this.personService.loginPerson(this.user, this.pass).subscribe(
        (resp) => {
          this.incorrect = false;
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
    this.goHome();
  }
  goHome() {
    this.isRegistering = false;
  }
  register(){
    this.isRegistering = true;
  }

  getLoggedPerson(){
    return this.personService.getLoggedPerson();
  }
}
