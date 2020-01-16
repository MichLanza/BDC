import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddIncComponent } from './add-inc.component';

describe('AddIncComponent', () => {
  let component: AddIncComponent;
  let fixture: ComponentFixture<AddIncComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddIncComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddIncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
