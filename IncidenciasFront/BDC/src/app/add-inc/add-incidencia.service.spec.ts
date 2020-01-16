import { TestBed, inject } from '@angular/core/testing';

import { AddIncidenciaService } from './add-incidencia.service';

describe('AddIncidenciaService', () => {
  beforeEach(() => TestBed.configureTestingModule({  providers: [AddIncidenciaService]}));

  it('should be created', inject([AddIncidenciaService], (service: AddIncidenciaService) => {
    
    expect(service).toBeTruthy();
  }));
});
