import {TestBed} from '@angular/core/testing';

import {AdminSongService} from './admin-song.service';

describe('AdminSongService', () => {
  let service: AdminSongService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminSongService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
