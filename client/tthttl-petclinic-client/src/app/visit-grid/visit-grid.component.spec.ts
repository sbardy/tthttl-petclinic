import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {VisitGridComponent} from './visit-grid.component';

describe('VisitGridComponent', () => {
  let component: VisitGridComponent;
  let fixture: ComponentFixture<VisitGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
