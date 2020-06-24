/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Leave } from '../models/leave';
import { LeaveDTO } from '../models/leave-dto';

/**
 * Leave Resource
 */
@Injectable({
  providedIn: 'root',
})
class LeaveResourceService extends __BaseService {
  static readonly getAllLeavesUsingGETPath = '/api/leaves';
  static readonly createLeaveUsingPOSTPath = '/api/leaves';
  static readonly updateLeaveUsingPUTPath = '/api/leaves';
  static readonly getLeaveUsingGETPath = '/api/leaves/{id}';
  static readonly deleteLeaveUsingDELETEPath = '/api/leaves/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllLeaves
   * @return OK
   */
  getAllLeavesUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Leave>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/leaves`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Leave>>;
      })
    );
  }
  /**
   * getAllLeaves
   * @return OK
   */
  getAllLeavesUsingGET(): __Observable<Array<Leave>> {
    return this.getAllLeavesUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Leave>)
    );
  }

  /**
   * createLeave
   * @param leaveDTO leaveDTO
   * @return OK
   */
  createLeaveUsingPOSTResponse(leaveDTO: LeaveDTO): __Observable<__StrictHttpResponse<Leave>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = leaveDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/leaves`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Leave>;
      })
    );
  }
  /**
   * createLeave
   * @param leaveDTO leaveDTO
   * @return OK
   */
  createLeaveUsingPOST(leaveDTO: LeaveDTO): __Observable<Leave> {
    return this.createLeaveUsingPOSTResponse(leaveDTO).pipe(
      __map(_r => _r.body as Leave)
    );
  }

  /**
   * updateLeave
   * @param leaveDTO leaveDTO
   * @return OK
   */
  updateLeaveUsingPUTResponse(leaveDTO: LeaveDTO): __Observable<__StrictHttpResponse<Leave>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = leaveDTO;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/leaves`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Leave>;
      })
    );
  }
  /**
   * updateLeave
   * @param leaveDTO leaveDTO
   * @return OK
   */
  updateLeaveUsingPUT(leaveDTO: LeaveDTO): __Observable<Leave> {
    return this.updateLeaveUsingPUTResponse(leaveDTO).pipe(
      __map(_r => _r.body as Leave)
    );
  }

  /**
   * getLeave
   * @param id id
   * @return OK
   */
  getLeaveUsingGETResponse(id: number): __Observable<__StrictHttpResponse<Leave>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/leaves/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Leave>;
      })
    );
  }
  /**
   * getLeave
   * @param id id
   * @return OK
   */
  getLeaveUsingGET(id: number): __Observable<Leave> {
    return this.getLeaveUsingGETResponse(id).pipe(
      __map(_r => _r.body as Leave)
    );
  }

  /**
   * deleteLeave
   * @param id id
   */
  deleteLeaveUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/leaves/${encodeURIComponent(id)}`,
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
   * deleteLeave
   * @param id id
   */
  deleteLeaveUsingDELETE(id: number): __Observable<null> {
    return this.deleteLeaveUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module LeaveResourceService {
}

export { LeaveResourceService }
