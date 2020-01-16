import { TestBed } from '@angular/core/testing';

import { ModService } from './mod-incidencia.service';

describe('ModService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ModService = TestBed.get(ModService);
    expect(service).toBeTruthy();
  });
});
