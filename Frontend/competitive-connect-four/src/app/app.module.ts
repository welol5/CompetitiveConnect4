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
import { GameService } from './services/game.service';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { QueueComponent } from './queue/queue.component';
import { ProfileComponent } from './profile/profile.component';
import { HistoryComponent } from './history/history.component';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    QueueComponent,
    ProfileComponent,
    HistoryComponent
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
    CookieService,
    GameService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
