import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor() { }
  getUrl(): string {
    return 'http://localhost:6401/CompetitiveConnect4/';
  }

  getWSUrl(): string {
    return 'ws://localhost:6401/CompetitiveConnect4/';
  }
}
