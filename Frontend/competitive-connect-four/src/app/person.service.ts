import { Injectable } from '@angular/core';
import { Person } from './models/Person';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private loggedPerson: Person;
  private personUrl: string;
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
    'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
    'Content-Type':'application/json'})

  constructor(private http: HttpClient,private urlService:UrlService, private cookieService: CookieService) {
    this.personUrl = this.urlService.getUrl() + 'person';
   }

   loginPerson(username: string, password: string): Observable<Person> {
    if (username && password) {
      const queryParams = `?user=${username}&pass=${password}`;
      return this.http.put(this.personUrl + queryParams,
        {headers: this.formHeaders, withCredentials:true}).pipe(
          map(resp => resp as Person)
      );
    } else {
      return this.http.get(this.personUrl,
        {withCredentials:true}).pipe(
          map(resp => resp as Person)
        );
    }
  }

  logoutPerson(): Observable<object> {
    return this.http.delete(this.personUrl, {headers:this.regHeaders, withCredentials:true}).pipe();
  }

  updatePerson(updatedPerson: Person): Observable<object> {
    this.loggedPerson = updatedPerson;
    return this.http.put(this.personUrl + this.loggedPerson.id, updatedPerson, 
      {headers:this.regHeaders, withCredentials:true}).pipe();
  }

  getLoggedPerson(): Person {
    return this.loggedPerson;
  }

}
