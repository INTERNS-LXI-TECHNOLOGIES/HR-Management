/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Appraisal } from '../models/appraisal';

/**
 * Appraisal Resource
 */
@Injectable({
  providedIn: 'root',
})
class AppraisalResourceService extends __BaseService {
  static readonly getAllAppraisalsUsingGETPath = '/api/appraisals';
  static readonly createAppraisalUsingPOSTPath = '/api/appraisals';
  static readonly updateAppraisalUsingPUTPath = '/api/appraisals';
  static readonly getAppraisalUsingGET1Path = '/api/appraisals/{id}';
  static readonly deleteAppraisalUsingDELETEPath = '/api/appraisals/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllAppraisals
   * @return OK
   */
  getAllAppraisalsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Appraisal>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisals`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Appraisal>>;
      })
    );
  }
  /**
   * getAllAppraisals
   * @return OK
   */
  getAllAppraisalsUsingGET(): __Observable<Array<Appraisal>> {
    return this.getAllAppraisalsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Appraisal>)
    );
  }

  /**
   * createAppraisal
   * @param appraisal appraisal
   * @return OK
   */
  createAppraisalUsingPOSTResponse(appraisal: Appraisal): __Observable<__StrictHttpResponse<Appraisal>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = appraisal;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/appraisals`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Appraisal>;
      })
    );
  }
  /**
   * createAppraisal
   * @param appraisal appraisal
   * @return OK
   */
  createAppraisalUsingPOST(appraisal: Appraisal): __Observable<Appraisal> {
    return this.createAppraisalUsingPOSTResponse(appraisal).pipe(
      __map(_r => _r.body as Appraisal)
    );
  }

  /**
   * updateAppraisal
   * @param appraisal appraisal
   * @return OK
   */
  updateAppraisalUsingPUTResponse(appraisal: Appraisal): __Observable<__StrictHttpResponse<Appraisal>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = appraisal;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/appraisals`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Appraisal>;
      })
    );
  }
  /**
   * updateAppraisal
   * @param appraisal appraisal
   * @return OK
   */
  updateAppraisalUsingPUT(appraisal: Appraisal): __Observable<Appraisal> {
    return this.updateAppraisalUsingPUTResponse(appraisal).pipe(
      __map(_r => _r.body as Appraisal)
    );
  }

  /**
   * getAppraisal
   * @param id id
   * @return OK
   */
  getAppraisalUsingGET1Response(id: number): __Observable<__StrictHttpResponse<Appraisal>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisals/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Appraisal>;
      })
    );
  }
  /**
   * getAppraisal
   * @param id id
   * @return OK
   */
  getAppraisalUsingGET1(id: number): __Observable<Appraisal> {
    return this.getAppraisalUsingGET1Response(id).pipe(
      __map(_r => _r.body as Appraisal)
    );
  }

  /**
   * deleteAppraisal
   * @param id id
   */
  deleteAppraisalUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/appraisals/${encodeURIComponent(id)}`,
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
   * deleteAppraisal
   * @param id id
   */
  deleteAppraisalUsingDELETE(id: number): __Observable<null> {
    return this.deleteAppraisalUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module AppraisalResourceService {
}

export { AppraisalResourceService }
