import { Injectable } from '@angular/core';
import { Person } from '../models/Person';
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
  private myStorage = window.localStorage;
  private formHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
    'Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders({'Cookie':this.cookieService.get('JSESSIONID'),
    'Content-Type':'application/json'})
    private headers = new HttpHeaders().set('access-control-allow-origin',"http://localhost:8080/");

  constructor(private http: HttpClient,private urlService:UrlService, private cookieService: CookieService) {
    this.personUrl = this.urlService.getUrl() + 'Backend_war_exploded/users';
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

  getPersonbyId(id: number): Observable<Person>{
    return this.http.get(this.personUrl + "/person/"+id,
    {withCredentials:true}).pipe(
      map(resp => resp as Person)
    );
  }

  logoutPerson(){
    this.myStorage.removeItem('person');
  }

  
  refreshPerson(){
    this.getPersonbyId(this.getLoggedPerson().id).subscribe(
      resp => {
        this.setLoggedPerson(resp);
        //console.log("REFRESHPERSON",resp);
      }
    );
  }

  updatePerson(updatedPerson: Person): Observable<object> {
    this.loggedPerson = updatedPerson;
    console.log(this.personUrl +"/"+ this.loggedPerson.id);
    return this.http.put(this.personUrl + "/"+this.loggedPerson.id, updatedPerson, 
      {headers:this.regHeaders, withCredentials:true}).pipe();
  }
  setLoggedPerson(person: Person){
    this.myStorage.setItem('person', JSON.stringify(person));
  }
  getLoggedPerson(): Person {
    return JSON.parse(this.myStorage.getItem('person'));
  }
  registerPerson(username: string, password: string, filepath: string): Observable<Person> {
    if (username && password) {
      const queryParams = `?user=${username}&pass=${password}&filepath=${filepath}`;
      return this.http.post(this.personUrl + queryParams,
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
  uploadFile(username: string, file: File): Observable<string> {
    let data: FormData = new FormData();
    data.append('file', file);
    return this.http.post(this.personUrl + `/picture?username=${username}`, data,
    {withCredentials:true}).pipe(
      map(resp => resp as string)
    );

  }

}
