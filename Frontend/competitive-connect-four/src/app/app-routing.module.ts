import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './game/game.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { HistoryComponent } from './history/history.component';


const routes: Routes = [
  {
    path: '**',
    component: HomeComponent
  },
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'game',
    component: GameComponent
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'register',
    component: RegisterComponent
  },
  {
    path:'profile',
    component: ProfileComponent
  },
  {
    path:'profile/history/:id',
    component: HistoryComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
