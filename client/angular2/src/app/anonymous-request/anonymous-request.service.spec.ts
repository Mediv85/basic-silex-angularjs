/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { AnonymousRequestService } from './anonymous-request.service';

describe('AnonymousRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AnonymousRequestService]
    });
  });

  it('should ...', inject([AnonymousRequestService], (service: AnonymousRequestService) => {
    expect(service).toBeTruthy();
  }));
});
