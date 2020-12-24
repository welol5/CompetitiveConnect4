import { Injectable, OnInit } from '@angular/core';
import { GameAction } from '../models/game-action';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class SocketioService{

  private webSocket : WebSocket;
  private url : string;

  constructor(private urlService: UrlService) {

    // this.url = this.urlService.getUrl() + 'CompetitiveConnect4_war_exploded/gameaction';
    // this.url = 'ws://echo.websocket.org';
    this.url = 'ws://localhost:8080/CompetitiveConnect4_war_exploded/gameaction';
    console.log(this.url);
  }

  openConnection(){
    console.log('openConnection');
    this.webSocket = new WebSocket(this.url);
    console.log('open',this.url);

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);
    };

    this.webSocket.onmessage = (event) => {
      //recive message
      const action = JSON.parse(event.data);
      console.log(action);
    };

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event);
    };
  }

  sendMessage(gameAction: GameAction){
    this.webSocket.send(JSON.stringify(gameAction));
  }

  closeConnection(){
    this.webSocket.close();
  }
}
