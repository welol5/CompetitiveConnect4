import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'competitive-connect-four';

  setLogin() {
    console.log('user logged in');
  }
}
