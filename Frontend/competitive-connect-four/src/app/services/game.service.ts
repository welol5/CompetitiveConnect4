import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { SocketioService } from './socketio.service';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class GameService implements OnInit {

  private socket;

  constructor(private http: HttpClient,private socketService: SocketioService) {}

  ngOnInit(){
    console.log('init gameService');
    this.socket = this.socketService.getSocket();
    console.log('success');
  }

  makeMove(row: number, col: number){
    //let url = this.urlService.getUrl() + '/CompetitiveConnect4_war_exploded/move';
    //console.log(url);

    // let 
  }
}
