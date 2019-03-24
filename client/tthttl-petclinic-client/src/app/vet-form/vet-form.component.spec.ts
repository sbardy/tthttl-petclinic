import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {VetFormComponent} from './vet-form.component';

describe('VetFormComponent', () => {
  let component: VetFormComponent;
  let fixture: ComponentFixture<VetFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VetFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
