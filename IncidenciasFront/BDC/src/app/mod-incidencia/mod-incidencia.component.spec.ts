import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModIncidenciaComponent } from './mod-incidencia.component';

describe('ModIncidenciaComponent', () => {
  let component: ModIncidenciaComponent;
  let fixture: ComponentFixture<ModIncidenciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModIncidenciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModIncidenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
