import { ComponentFixture, TestBed } from '@angular/core/testing';
import { by, element } from 'protractor';

import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let usernameField = element(by.id('user'));
  let passwordField = element(by.id('pass'));
  let registerButton = element(by.id('registerBtn'));

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(usernameField).toBeTruthy();
    expect(passwordField).toBeTruthy();
    expect(registerButton).toBeTruthy();
  });
});
