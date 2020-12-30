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
  currentFileUpload: File;
  filepath: string;

  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit(): void {
    this.user = '';
    this.pass = '';
  }
  register() {
    console.log(this.currentFileUpload);
    if (this.currentFileUpload) {
      
      this.personService.uploadFile(this.user, this.currentFileUpload).subscribe(
        res =>{
          
        }
      );
      let newFilePath
      newFilePath = `..3/Pictures/${this.user}/${this.user}.${this.currentFileUpload.type.split("/")[1]}`
      console.log(this.filepath);
      this.personService.registerPerson(this.user, this.pass, newFilePath).subscribe(
        resp => {
          this.loggedPerson = resp;
          this.router.navigate(['home']);
        }
      )
    } else {
      this.personService.registerPerson(this.user, this.pass, "").subscribe(
        resp => {
          this.loggedPerson = resp;
          this.router.navigate(['home']);
        }
      )
    }
  }
  attach(image: any) {
    console.log("in the attacher ");
    
    this.currentFileUpload = image.files[0];

  }
}
