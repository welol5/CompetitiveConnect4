import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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
  }

  getLoggedPerson(){
    return this.personService.getLoggedPerson();
  }
}
