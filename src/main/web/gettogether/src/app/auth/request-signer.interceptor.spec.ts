import { TestBed } from '@angular/core/testing';

import { RequestSignerInterceptor } from './request-signer.interceptor';

describe('RequestSignerInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      RequestSignerInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: RequestSignerInterceptor = TestBed.inject(RequestSignerInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
