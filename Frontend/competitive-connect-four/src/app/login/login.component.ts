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

  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit(): void {
    console.log("login shows");
    this.user = '';
    this.pass = '';
    this.logIn();
  }
  ngOnChanges() {
    console.log(this.user + ' ' + this.pass);
  }
  logIn() {
    console.log(this.user + ' ' + this.pass);
    this.personService.loginPerson(this.user, this.pass).subscribe(
      resp => {
        this.loggedPerson = resp;
        this.personService.setLoggedPerson(resp);
        this.logInEvent.emit();
      }
    );
  }
  logOut() {
    this.personService.logoutPerson().subscribe(
      resp => {
        this.loggedPerson = null;
        this.goHome();
      }
    );
  }
  goHome(){
    this.router.navigate(['home']);
  }
}
