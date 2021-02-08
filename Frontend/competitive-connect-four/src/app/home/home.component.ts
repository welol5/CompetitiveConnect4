import { Component, OnInit } from '@angular/core';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

import { HistoryService } from '../services/history.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  leaderboard = [];
  private historyArray = [];
  loggedPerson: Person;
  constructor(private historyService: HistoryService,private personService: PersonService) { }
  ngOnInit(): void {
    this.generateLeaderboard();
    // console.log(this.leaderboard);
  }
  generateLeaderboard() {

    let leaderboardBuilder= [];
    this.historyService.getHistoryToday().subscribe(
      res => {
        this.historyArray = res;
        // console.log(this.historyArray[0]);
        for (let gameHistory of this.historyArray) {
          let user = gameHistory.winner.username;
          if (leaderboardBuilder[user]) {
            leaderboardBuilder[user].wins ++;
          } else {
            let newWinner = {
              
              wins: 1,
              winner: gameHistory.winner
            }
            leaderboardBuilder[user] = newWinner;
            // console.log(leaderboardBuilder[user])
          }
        }
        // console.log(leaderboardBuilder.length)
        for (let winnerStr in leaderboardBuilder) {
          // console.log(winnerObj);
          this.leaderboard.push(leaderboardBuilder[winnerStr]);
        }
        this.leaderboard.sort((a, b) => b.wins - a.wins);
        // console.log (this.leaderboard[0].wins);

      }
    )
  }

}
