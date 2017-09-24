import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobexComponent } from './jobex.component';

describe('JobexComponent', () => {
  let component: JobexComponent;
  let fixture: ComponentFixture<JobexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
