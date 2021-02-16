import { TestBed } from '@angular/core/testing';

import { UrlService } from './url.service';

describe('UrlService', () => {
  let service: UrlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UrlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  // it('should print "http://localhost:8080/"', () => {
  //   let url =  service.getUrl();
  //   let url2 =  "http://localhost:8080/";
  //   expect(url).toEqual(url2);
  // });
});
