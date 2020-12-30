import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Person } from '../models/Person';
import { CookieService } from 'ngx-cookie-service';
import { UrlService } from './url.service';
import { GameHistory } from '../models/game-history';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {
  private historyUrl: string;
  private myStorage = window.localStorage;
  // private historyArray: GameHistory[];
  // private 
  // private ;

  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
  'Content-Type':'application/json'});

  constructor(private http: HttpClient,private urlService:UrlService, private cookieService: CookieService) { 
    this.historyUrl = this.urlService.getUrl() + 'Backend_war_exploded/history';
  }

  getHistoryToday(): Observable<GameHistory[]> {

    return this.http.get(this.historyUrl).pipe(
      map(resp => resp as GameHistory[])
    );
  }
  getGameHistory(loggedPerson: Person): Observable<GameHistory[]> {
    console.log(this.historyUrl+"/users/"+loggedPerson.id);
    // console.log(GameHistory[0]);
    return this.http.get(this.historyUrl+"/user/"+loggedPerson.id,{withCredentials: true}).pipe(
      map(resp => resp as GameHistory[])
    );
   
  }
  getLeaderboard() {
    let historyArray = [];
    let leaderboardBuilder= [];
    let leaderboard = [];
    this.getHistoryToday().subscribe(history => {
      
      historyArray = history as GameHistory[]
      
    });
    console.log(historyArray);
    for (let gameHistory of historyArray) {
      let user = gameHistory.winner.username;
      if (leaderboardBuilder[user]) {
        leaderboardBuilder[user].wins ++;
      } else {
        let newWinner = {
          
          wins: 1,
          winner: gameHistory.winner
        }
        leaderboardBuilder[user] = newWinner;
      }
    }
    for (let i = 0; i < 10; i++) {
      
      let top = leaderboardBuilder.reduce((max, game) => max.wins > game.wins ? max : game);
      let winnerName:string
      winnerName = top.winner.name;
      leaderboard[i] = top;
      leaderboardBuilder[winnerName] = null;

      // let maxWins = Math.max(...this.leaderboardBuilder.map(e => e.wins));
      // let winnerObj = this.leaderboardBuilder.find(game => game.wins === maxWins);

    }
    console.log("this is the leaderboard" + leaderboard);
    return leaderboard;
  }



}
