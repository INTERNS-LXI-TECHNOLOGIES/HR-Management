/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { LateArrival } from '../models/late-arrival';
import { LateDTO } from '../models/late-dto';

/**
 * Late Arrival Resource
 */
@Injectable({
  providedIn: 'root',
})
class LateArrivalResourceService extends __BaseService {
  static readonly getAllLateArrivalsUsingGETPath = '/api/late-arrivals';
  static readonly createLateArrivalUsingPOSTPath = '/api/late-arrivals';
  static readonly updateLateArrivalUsingPUTPath = '/api/late-arrivals';
  static readonly getLateArrivalUsingGETPath = '/api/late-arrivals/{id}';
  static readonly deleteLateArrivalUsingDELETEPath = '/api/late-arrivals/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllLateArrivals
   * @return OK
   */
  getAllLateArrivalsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<LateArrival>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/late-arrivals`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LateArrival>>;
      })
    );
  }
  /**
   * getAllLateArrivals
   * @return OK
   */
  getAllLateArrivalsUsingGET(): __Observable<Array<LateArrival>> {
    return this.getAllLateArrivalsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<LateArrival>)
    );
  }

  /**
   * createLateArrival
   * @param lateDTO lateDTO
   * @return OK
   */
  createLateArrivalUsingPOSTResponse(lateDTO: LateDTO): __Observable<__StrictHttpResponse<LateArrival>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = lateDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/late-arrivals`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<LateArrival>;
      })
    );
  }
  /**
   * createLateArrival
   * @param lateDTO lateDTO
   * @return OK
   */
  createLateArrivalUsingPOST(lateDTO: LateDTO): __Observable<LateArrival> {
    return this.createLateArrivalUsingPOSTResponse(lateDTO).pipe(
      __map(_r => _r.body as LateArrival)
    );
  }

  /**
   * updateLateArrival
   * @param lateArrival lateArrival
   * @return OK
   */
  updateLateArrivalUsingPUTResponse(lateArrival: LateArrival): __Observable<__StrictHttpResponse<LateArrival>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = lateArrival;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/late-arrivals`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<LateArrival>;
      })
    );
  }
  /**
   * updateLateArrival
   * @param lateArrival lateArrival
   * @return OK
   */
  updateLateArrivalUsingPUT(lateArrival: LateArrival): __Observable<LateArrival> {
    return this.updateLateArrivalUsingPUTResponse(lateArrival).pipe(
      __map(_r => _r.body as LateArrival)
    );
  }

  /**
   * getLateArrival
   * @param id id
   * @return OK
   */
  getLateArrivalUsingGETResponse(id: number): __Observable<__StrictHttpResponse<LateArrival>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/late-arrivals/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<LateArrival>;
      })
    );
  }
  /**
   * getLateArrival
   * @param id id
   * @return OK
   */
  getLateArrivalUsingGET(id: number): __Observable<LateArrival> {
    return this.getLateArrivalUsingGETResponse(id).pipe(
      __map(_r => _r.body as LateArrival)
    );
  }

  /**
   * deleteLateArrival
   * @param id id
   */
  deleteLateArrivalUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/late-arrivals/${encodeURIComponent(id)}`,
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
   * deleteLateArrival
   * @param id id
   */
  deleteLateArrivalUsingDELETE(id: number): __Observable<null> {
    return this.deleteLateArrivalUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module LateArrivalResourceService {
}

export { LateArrivalResourceService }
