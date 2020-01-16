import { TestBed } from '@angular/core/testing';

import { ReadTableService } from './read-table.service';

describe('ReadTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReadTableService = TestBed.get(ReadTableService);
    expect(service).toBeTruthy();
  });
});
