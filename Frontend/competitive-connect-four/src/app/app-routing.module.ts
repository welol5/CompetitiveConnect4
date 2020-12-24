import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './game/game.component';
<<<<<<< HEAD
import { HomeComponent } from './home/home.component';
=======
import { RegisterComponent } from './register/register.component';
>>>>>>> 394824a2ca420eb269efb73192703e79ae8f8a61

const routes: Routes = [
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
