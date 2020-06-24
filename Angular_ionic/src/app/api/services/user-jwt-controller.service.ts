/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { JWTToken } from '../models/jwttoken';
import { LoginVM } from '../models/login-vm';
import { Authentication } from '../models/authentication';
import { User } from '../models/user';

/**
 * User JWT Controller
 */
@Injectable({
  providedIn: 'root',
})
class UserJwtControllerService extends __BaseService {
  static readonly authenticateUsingGETPath = '/api/auth';
  static readonly authorizeUsingPOSTPath = '/api/authenticate';
  static readonly getAuthUsingGETPath = '/api/getAuth';
  static readonly getUserUsingGETPath = '/api/getuser';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * authenticate
   */
  authenticateUsingGETResponse(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/auth`,
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
   * authenticate
   */
  authenticateUsingGET(): __Observable<null> {
    return this.authenticateUsingGETResponse().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * authorize
   * @param loginVM loginVM
   * @return OK
   */
  authorizeUsingPOSTResponse(loginVM: LoginVM): __Observable<__StrictHttpResponse<JWTToken>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = loginVM;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/authenticate`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<JWTToken>;
      })
    );
  }
  /**
   * authorize
   * @param loginVM loginVM
   * @return OK
   */
  authorizeUsingPOST(loginVM: LoginVM): __Observable<JWTToken> {
    return this.authorizeUsingPOSTResponse(loginVM).pipe(
      __map(_r => _r.body as JWTToken)
    );
  }

  /**
   * getAuth
   * @param token token
   * @return OK
   */
  getAuthUsingGETResponse(token: string): __Observable<__StrictHttpResponse<Authentication>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = token;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/getAuth`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Authentication>;
      })
    );
  }
  /**
   * getAuth
   * @param token token
   * @return OK
   */
  getAuthUsingGET(token: string): __Observable<Authentication> {
    return this.getAuthUsingGETResponse(token).pipe(
      __map(_r => _r.body as Authentication)
    );
  }

  /**
   * getUser
   * @param username username
   * @return OK
   */
  getUserUsingGETResponse(username: string): __Observable<__StrictHttpResponse<User>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = username;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/getuser`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<User>;
      })
    );
  }
  /**
   * getUser
   * @param username username
   * @return OK
   */
  getUserUsingGET(username: string): __Observable<User> {
    return this.getUserUsingGETResponse(username).pipe(
      __map(_r => _r.body as User)
    );
  }
}

module UserJwtControllerService {
}

export { UserJwtControllerService }
