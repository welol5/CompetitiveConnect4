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
  cpass: string;
  currentFileUpload: File;
  filepath: string;

  hasError: boolean = false;
  errorMessage: string = 'No Error';

  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit(): void {
    this.user = '';
    this.pass = '';
  }
  register() {
    if(this.pass === this.cpass){
      this.personService.registerPerson(this.user, this.pass).subscribe(
        (resp) => {
          this.loggedPerson = resp;
          this.router.navigate(['home']);
        },
        (error) => {
          this.hasError = true;
          this.errorMessage = 'Username already taken';
        }
      );
    } else {
      this.hasError = true;
      this.errorMessage = 'Passwords do not match';
    }
  }
  attach(image: any) {
    console.log("in the attacher ");

    this.currentFileUpload = image.files[0];

  }
}
