import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {VetGridComponent} from './vet-grid.component';

describe('VetGridComponent', () => {
  let component: VetGridComponent;
  let fixture: ComponentFixture<VetGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VetGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VetGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
