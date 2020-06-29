/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { UserExtra } from '../models/user-extra';

/**
 * User Extra Resource
 */
@Injectable({
  providedIn: 'root',
})
class UserExtraResourceService extends __BaseService {
  static readonly getAllUserExtrasUsingGETPath = '/api/user-extras';
  static readonly createUserExtraUsingPOSTPath = '/api/user-extras';
  static readonly updateUserExtraUsingPUTPath = '/api/user-extras';
  static readonly getUserExtraUsingGET1Path = '/api/user-extras/{id}';
  static readonly deleteUserExtraUsingDELETEPath = '/api/user-extras/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllUserExtras
   * @return OK
   */
  getAllUserExtrasUsingGETResponse(): __Observable<__StrictHttpResponse<Array<UserExtra>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-extras`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<UserExtra>>;
      })
    );
  }
  /**
   * getAllUserExtras
   * @return OK
   */
  getAllUserExtrasUsingGET(): __Observable<Array<UserExtra>> {
    return this.getAllUserExtrasUsingGETResponse().pipe(
      __map(_r => _r.body as Array<UserExtra>)
    );
  }

  /**
   * createUserExtra
   * @param userExtra userExtra
   * @return OK
   */
  createUserExtraUsingPOSTResponse(userExtra: UserExtra): __Observable<__StrictHttpResponse<UserExtra>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userExtra;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/user-extras`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserExtra>;
      })
    );
  }
  /**
   * createUserExtra
   * @param userExtra userExtra
   * @return OK
   */
  createUserExtraUsingPOST(userExtra: UserExtra): __Observable<UserExtra> {
    return this.createUserExtraUsingPOSTResponse(userExtra).pipe(
      __map(_r => _r.body as UserExtra)
    );
  }

  /**
   * updateUserExtra
   * @param userExtra userExtra
   * @return OK
   */
  updateUserExtraUsingPUTResponse(userExtra: UserExtra): __Observable<__StrictHttpResponse<UserExtra>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userExtra;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/user-extras`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserExtra>;
      })
    );
  }
  /**
   * updateUserExtra
   * @param userExtra userExtra
   * @return OK
   */
  updateUserExtraUsingPUT(userExtra: UserExtra): __Observable<UserExtra> {
    return this.updateUserExtraUsingPUTResponse(userExtra).pipe(
      __map(_r => _r.body as UserExtra)
    );
  }

  /**
   * getUserExtra
   * @param id id
   * @return OK
   */
  getUserExtraUsingGET1Response(id: number): __Observable<__StrictHttpResponse<UserExtra>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-extras/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserExtra>;
      })
    );
  }
  /**
   * getUserExtra
   * @param id id
   * @return OK
   */
  getUserExtraUsingGET1(id: number): __Observable<UserExtra> {
    return this.getUserExtraUsingGET1Response(id).pipe(
      __map(_r => _r.body as UserExtra)
    );
  }

  /**
   * deleteUserExtra
   * @param id id
   */
  deleteUserExtraUsingDELETEResponse(id: string): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/user-extras/${encodeURIComponent(id)}`,
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
   * deleteUserExtra
   * @param id id
   */
  deleteUserExtraUsingDELETE(id: string): __Observable<null> {
    return this.deleteUserExtraUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module UserExtraResourceService {
}

export { UserExtraResourceService }
