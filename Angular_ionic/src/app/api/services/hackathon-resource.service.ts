/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Hackathon } from '../models/hackathon';
import { HackDTO } from '../models/hack-dto';

/**
 * Hackathon Resource
 */
@Injectable({
  providedIn: 'root',
})
class HackathonResourceService extends __BaseService {
  static readonly getAllHackathonsUsingGETPath = '/api/hackathons';
  static readonly createHackathonUsingPOSTPath = '/api/hackathons';
  static readonly updateHackathonUsingPUTPath = '/api/hackathons';
  static readonly getHackathonUsingGETPath = '/api/hackathons/{id}';
  static readonly deleteHackathonUsingDELETEPath = '/api/hackathons/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllHackathons
   * @return OK
   */
  getAllHackathonsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Hackathon>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/hackathons`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Hackathon>>;
      })
    );
  }
  /**
   * getAllHackathons
   * @return OK
   */
  getAllHackathonsUsingGET(): __Observable<Array<Hackathon>> {
    return this.getAllHackathonsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Hackathon>)
    );
  }

  /**
   * createHackathon
   * @param hackDTO hackDTO
   * @return OK
   */
  createHackathonUsingPOSTResponse(hackDTO: HackDTO): __Observable<__StrictHttpResponse<Hackathon>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = hackDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/hackathons`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Hackathon>;
      })
    );
  }
  /**
   * createHackathon
   * @param hackDTO hackDTO
   * @return OK
   */
  createHackathonUsingPOST(hackDTO: HackDTO): __Observable<Hackathon> {
    return this.createHackathonUsingPOSTResponse(hackDTO).pipe(
      __map(_r => _r.body as Hackathon)
    );
  }

  /**
   * updateHackathon
   * @param hackathon hackathon
   * @return OK
   */
  updateHackathonUsingPUTResponse(hackathon: Hackathon): __Observable<__StrictHttpResponse<Hackathon>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = hackathon;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/hackathons`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Hackathon>;
      })
    );
  }
  /**
   * updateHackathon
   * @param hackathon hackathon
   * @return OK
   */
  updateHackathonUsingPUT(hackathon: Hackathon): __Observable<Hackathon> {
    return this.updateHackathonUsingPUTResponse(hackathon).pipe(
      __map(_r => _r.body as Hackathon)
    );
  }

  /**
   * getHackathon
   * @param id id
   * @return OK
   */
  getHackathonUsingGETResponse(id: number): __Observable<__StrictHttpResponse<Hackathon>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/hackathons/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Hackathon>;
      })
    );
  }
  /**
   * getHackathon
   * @param id id
   * @return OK
   */
  getHackathonUsingGET(id: number): __Observable<Hackathon> {
    return this.getHackathonUsingGETResponse(id).pipe(
      __map(_r => _r.body as Hackathon)
    );
  }

  /**
   * deleteHackathon
   * @param id id
   */
  deleteHackathonUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/hackathons/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * deleteHackathon
   * @param id id
   */
  deleteHackathonUsingDELETE(id: number): __Observable<null> {
    return this.deleteHackathonUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module HackathonResourceService {
}

export { HackathonResourceService }
