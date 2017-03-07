/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SecretRequestService } from './secret-request.service';

describe('SecretRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SecretRequestService]
    });
  });

  it('should ...', inject([SecretRequestService], (service: SecretRequestService) => {
    expect(service).toBeTruthy();
  }));
});
