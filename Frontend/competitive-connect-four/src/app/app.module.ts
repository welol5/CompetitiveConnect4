import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { GameComponent } from './game/game.component';
import { LoginComponent } from './login/login.component';
import { PersonService } from './services/person.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UrlService } from './services/url.service';
import { CookieService } from 'ngx-cookie-service';
import { AppRoutingModule } from './app-routing.module';
<<<<<<< HEAD
import { HomeComponent } from './home/home.component';
=======
import { RegisterComponent } from './register/register.component';
>>>>>>> 394824a2ca420eb269efb73192703e79ae8f8a61

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    PersonService,
    UrlService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
