import { ComponentFixture, TestBed, tick } from '@angular/core/testing';
import { asyncData } from 'coverage/competitive-connect-four/src/testing/async-observable-helper';
import { of } from 'rxjs';
import { Observable } from 'rxjs';
import { Person } from '../models/Person';
import { PersonService } from '../services/person.service';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  let loginServiceSpy: jasmine.SpyObj<PersonService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set return a Person who is logged in', () => {
    loginServiceSpy = jasmine.createSpyObj('PersonService', ['loginPerson']);
    let testPerson: Person = new Person();
    testPerson.username = 'testUsername';
    testPerson.password = 'testPassword';
    loginServiceSpy.loginPerson.and.returnValue(asyncData(testPerson));

    let component = new LoginComponent(loginServiceSpy as any);

    component.user = 'testUsername';
    component.pass = 'testPassword';
    component.logIn();
  });
});