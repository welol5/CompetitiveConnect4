import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QueueComponent } from './queue.component';

describe('QueueComponent', () => {
  let component: QueueComponent;
  let fixture: ComponentFixture<QueueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QueueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QueueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });

  // it('should be unable to queue', () => {
  //   component.queue();
  //   let errorMessage = document.getElementById('error').innerHTML;
  //   expect(errorMessage).toBe('You must be logged in to queue');
  // });
});
