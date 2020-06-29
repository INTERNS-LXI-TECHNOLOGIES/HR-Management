/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { ReportStatus } from '../models/report-status';
import { ReportDTO } from '../models/report-dto';

/**
 * Report Status Resource
 */
@Injectable({
  providedIn: 'root',
})
class ReportStatusResourceService extends __BaseService {
  static readonly getAllReportStatusesUsingGETPath = '/api/report-statuses';
  static readonly createReportStatusUsingPOSTPath = '/api/report-statuses';
  static readonly updateReportStatusUsingPUTPath = '/api/report-statuses';
  static readonly getReportStatusUsingGETPath = '/api/report-statuses/{id}';
  static readonly deleteReportStatusUsingDELETEPath = '/api/report-statuses/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllReportStatuses
   * @return OK
   */
  getAllReportStatusesUsingGETResponse(): __Observable<__StrictHttpResponse<Array<ReportStatus>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/report-statuses`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ReportStatus>>;
      })
    );
  }
  /**
   * getAllReportStatuses
   * @return OK
   */
  getAllReportStatusesUsingGET(): __Observable<Array<ReportStatus>> {
    return this.getAllReportStatusesUsingGETResponse().pipe(
      __map(_r => _r.body as Array<ReportStatus>)
    );
  }

  /**
   * createReportStatus
   * @param reportDTO reportDTO
   * @return OK
   */
  createReportStatusUsingPOSTResponse(reportDTO: ReportDTO): __Observable<__StrictHttpResponse<ReportStatus>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = reportDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/report-statuses`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ReportStatus>;
      })
    );
  }
  /**
   * createReportStatus
   * @param reportDTO reportDTO
   * @return OK
   */
  createReportStatusUsingPOST(reportDTO: ReportDTO): __Observable<ReportStatus> {
    return this.createReportStatusUsingPOSTResponse(reportDTO).pipe(
      __map(_r => _r.body as ReportStatus)
    );
  }

  /**
   * updateReportStatus
   * @param reportStatus reportStatus
   * @return OK
   */
  updateReportStatusUsingPUTResponse(reportStatus: ReportStatus): __Observable<__StrictHttpResponse<ReportStatus>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = reportStatus;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/report-statuses`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ReportStatus>;
      })
    );
  }
  /**
   * updateReportStatus
   * @param reportStatus reportStatus
   * @return OK
   */
  updateReportStatusUsingPUT(reportStatus: ReportStatus): __Observable<ReportStatus> {
    return this.updateReportStatusUsingPUTResponse(reportStatus).pipe(
      __map(_r => _r.body as ReportStatus)
    );
  }

  /**
   * getReportStatus
   * @param id id
   * @return OK
   */
  getReportStatusUsingGETResponse(id: number): __Observable<__StrictHttpResponse<ReportStatus>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/report-statuses/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ReportStatus>;
      })
    );
  }
  /**
   * getReportStatus
   * @param id id
   * @return OK
   */
  getReportStatusUsingGET(id: number): __Observable<ReportStatus> {
    return this.getReportStatusUsingGETResponse(id).pipe(
      __map(_r => _r.body as ReportStatus)
    );
  }

  /**
   * deleteReportStatus
   * @param id id
   */
  deleteReportStatusUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/report-statuses/${encodeURIComponent(id)}`,
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
   * deleteReportStatus
   * @param id id
   */
  deleteReportStatusUsingDELETE(id: number): __Observable<null> {
    return this.deleteReportStatusUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module ReportStatusResourceService {
}

export { ReportStatusResourceService }
