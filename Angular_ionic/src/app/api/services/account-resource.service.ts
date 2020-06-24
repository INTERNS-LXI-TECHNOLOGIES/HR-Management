/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { UserDTO } from '../models/user-dto';
import { PasswordChangeDTO } from '../models/password-change-dto';
import { KeyAndPasswordVM } from '../models/key-and-password-vm';
import { PersistentToken } from '../models/persistent-token';
import { ManagedUserVM } from '../models/managed-user-vm';

/**
 * Account Resource
 */
@Injectable({
  providedIn: 'root',
})
class AccountResourceService extends __BaseService {
  static readonly getAccountUsingGETPath = '/api/account';
  static readonly saveAccountUsingPOSTPath = '/api/account';
  static readonly changePasswordUsingPOSTPath = '/api/account/change-password';
  static readonly finishPasswordResetUsingPOSTPath = '/api/account/reset-password/finish';
  static readonly requestPasswordResetUsingPOSTPath = '/api/account/reset-password/init';
  static readonly getCurrentSessionsUsingGETPath = '/api/account/sessions';
  static readonly invalidateSessionUsingDELETEPath = '/api/account/sessions/{series}';
  static readonly activateAccountUsingGETPath = '/api/activate';
  static readonly isAuthenticatedUsingGETPath = '/api/authenticate';
  static readonly registerAccountUsingPOSTPath = '/api/register';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAccount
   * @return OK
   */
  getAccountUsingGETResponse(): __Observable<__StrictHttpResponse<UserDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/account`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDTO>;
      })
    );
  }
  /**
   * getAccount
   * @return OK
   */
  getAccountUsingGET(): __Observable<UserDTO> {
    return this.getAccountUsingGETResponse().pipe(
      __map(_r => _r.body as UserDTO)
    );
  }

  /**
   * saveAccount
   * @param userDTO userDTO
   */
  saveAccountUsingPOSTResponse(userDTO: UserDTO): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/account`,
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
   * saveAccount
   * @param userDTO userDTO
   */
  saveAccountUsingPOST(userDTO: UserDTO): __Observable<null> {
    return this.saveAccountUsingPOSTResponse(userDTO).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * changePassword
   * @param passwordChangeDto passwordChangeDto
   */
  changePasswordUsingPOSTResponse(passwordChangeDto: PasswordChangeDTO): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = passwordChangeDto;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/account/change-password`,
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
   * changePassword
   * @param passwordChangeDto passwordChangeDto
   */
  changePasswordUsingPOST(passwordChangeDto: PasswordChangeDTO): __Observable<null> {
    return this.changePasswordUsingPOSTResponse(passwordChangeDto).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * finishPasswordReset
   * @param keyAndPassword keyAndPassword
   */
  finishPasswordResetUsingPOSTResponse(keyAndPassword: KeyAndPasswordVM): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = keyAndPassword;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/account/reset-password/finish`,
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
   * finishPasswordReset
   * @param keyAndPassword keyAndPassword
   */
  finishPasswordResetUsingPOST(keyAndPassword: KeyAndPasswordVM): __Observable<null> {
    return this.finishPasswordResetUsingPOSTResponse(keyAndPassword).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * requestPasswordReset
   * @param mail mail
   */
  requestPasswordResetUsingPOSTResponse(mail: string): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = mail;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/account/reset-password/init`,
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
   * requestPasswordReset
   * @param mail mail
   */
  requestPasswordResetUsingPOST(mail: string): __Observable<null> {
    return this.requestPasswordResetUsingPOSTResponse(mail).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * getCurrentSessions
   * @return OK
   */
  getCurrentSessionsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<PersistentToken>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/account/sessions`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<PersistentToken>>;
      })
    );
  }
  /**
   * getCurrentSessions
   * @return OK
   */
  getCurrentSessionsUsingGET(): __Observable<Array<PersistentToken>> {
    return this.getCurrentSessionsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<PersistentToken>)
    );
  }

  /**
   * invalidateSession
   * @param series series
   */
  invalidateSessionUsingDELETEResponse(series: string): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/account/sessions/${encodeURIComponent(series)}`,
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
   * invalidateSession
   * @param series series
   */
  invalidateSessionUsingDELETE(series: string): __Observable<null> {
    return this.invalidateSessionUsingDELETEResponse(series).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * activateAccount
   * @param key key
   */
  activateAccountUsingGETResponse(key: string): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (key != null) __params = __params.set('key', key.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/activate`,
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
   * activateAccount
   * @param key key
   */
  activateAccountUsingGET(key: string): __Observable<null> {
    return this.activateAccountUsingGETResponse(key).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * isAuthenticated
   * @return OK
   */
  isAuthenticatedUsingGETResponse(): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/authenticate`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<string>;
      })
    );
  }
  /**
   * isAuthenticated
   * @return OK
   */
  isAuthenticatedUsingGET(): __Observable<string> {
    return this.isAuthenticatedUsingGETResponse().pipe(
      __map(_r => _r.body as string)
    );
  }

  /**
   * registerAccount
   * @param managedUserVM managedUserVM
   */
  registerAccountUsingPOSTResponse(managedUserVM: ManagedUserVM): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = managedUserVM;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/register`,
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
   * registerAccount
   * @param managedUserVM managedUserVM
   */
  registerAccountUsingPOST(managedUserVM: ManagedUserVM): __Observable<null> {
    return this.registerAccountUsingPOSTResponse(managedUserVM).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module AccountResourceService {
}

export { AccountResourceService }
