/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Jira } from '../models/jira';
import { JiraDTO } from '../models/jira-dto';

/**
 * Jira Resource
 */
@Injectable({
  providedIn: 'root',
})
class JiraResourceService extends __BaseService {
  static readonly getAllJirasUsingGETPath = '/api/jiras';
  static readonly createJiraUsingPOSTPath = '/api/jiras';
  static readonly updateJiraUsingPUTPath = '/api/jiras';
  static readonly getJiraUsingGETPath = '/api/jiras/{id}';
  static readonly deleteJiraUsingDELETEPath = '/api/jiras/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllJiras
   * @return OK
   */
  getAllJirasUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Jira>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/jiras`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Jira>>;
      })
    );
  }
  /**
   * getAllJiras
   * @return OK
   */
  getAllJirasUsingGET(): __Observable<Array<Jira>> {
    return this.getAllJirasUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Jira>)
    );
  }

  /**
   * createJira
   * @param jiraDTO jiraDTO
   * @return OK
   */
  createJiraUsingPOSTResponse(jiraDTO: JiraDTO): __Observable<__StrictHttpResponse<Jira>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = jiraDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/jiras`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Jira>;
      })
    );
  }
  /**
   * createJira
   * @param jiraDTO jiraDTO
   * @return OK
   */
  createJiraUsingPOST(jiraDTO: JiraDTO): __Observable<Jira> {
    return this.createJiraUsingPOSTResponse(jiraDTO).pipe(
      __map(_r => _r.body as Jira)
    );
  }

  /**
   * updateJira
   * @param jiraDTO jiraDTO
   * @return OK
   */
  updateJiraUsingPUTResponse(jiraDTO: JiraDTO): __Observable<__StrictHttpResponse<Jira>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = jiraDTO;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/jiras`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Jira>;
      })
    );
  }
  /**
   * updateJira
   * @param jiraDTO jiraDTO
   * @return OK
   */
  updateJiraUsingPUT(jiraDTO: JiraDTO): __Observable<Jira> {
    return this.updateJiraUsingPUTResponse(jiraDTO).pipe(
      __map(_r => _r.body as Jira)
    );
  }

  /**
   * getJira
   * @param id id
   * @return OK
   */
  getJiraUsingGETResponse(id: number): __Observable<__StrictHttpResponse<Jira>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/jiras/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Jira>;
      })
    );
  }
  /**
   * getJira
   * @param id id
   * @return OK
   */
  getJiraUsingGET(id: number): __Observable<Jira> {
    return this.getJiraUsingGETResponse(id).pipe(
      __map(_r => _r.body as Jira)
    );
  }

  /**
   * deleteJira
   * @param id id
   */
  deleteJiraUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/jiras/${encodeURIComponent(id)}`,
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
   * deleteJira
   * @param id id
   */
  deleteJiraUsingDELETE(id: number): __Observable<null> {
    return this.deleteJiraUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module JiraResourceService {
}

export { JiraResourceService }
