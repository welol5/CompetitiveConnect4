import { Injectable, OnInit } from '@angular/core';
import * as io from 'socket.io-client';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class SocketioService implements OnInit{

  private socket;

  constructor(private urlService: UrlService) {

  }
  ngOnInit(): void {
    
  }

  getSocket(){
    return this.socket;
  }
}
