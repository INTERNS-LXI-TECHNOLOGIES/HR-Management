/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { UserDataBean } from '../models/user-data-bean';

/**
 * User Data Bean Resource
 */
@Injectable({
  providedIn: 'root',
})
class UserDataBeanResourceService extends __BaseService {
  static readonly getAllUserDataBeansUsingGETPath = '/api/user-data-beans';
  static readonly createUserDataBeanUsingPOSTPath = '/api/user-data-beans';
  static readonly updateUserDataBeanUsingPUTPath = '/api/user-data-beans';
  static readonly getUserDataBeanUsingGETPath = '/api/user-data-beans/{id}';
  static readonly deleteUserDataBeanUsingDELETEPath = '/api/user-data-beans/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllUserDataBeans
   * @return OK
   */
  getAllUserDataBeansUsingGETResponse(): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-data-beans`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<UserDataBean>>;
      })
    );
  }
  /**
   * getAllUserDataBeans
   * @return OK
   */
  getAllUserDataBeansUsingGET(): __Observable<Array<UserDataBean>> {
    return this.getAllUserDataBeansUsingGETResponse().pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * createUserDataBean
   * @param userDataBean userDataBean
   * @return OK
   */
  createUserDataBeanUsingPOSTResponse(userDataBean: UserDataBean): __Observable<__StrictHttpResponse<UserDataBean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDataBean;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/user-data-beans`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDataBean>;
      })
    );
  }
  /**
   * createUserDataBean
   * @param userDataBean userDataBean
   * @return OK
   */
  createUserDataBeanUsingPOST(userDataBean: UserDataBean): __Observable<UserDataBean> {
    return this.createUserDataBeanUsingPOSTResponse(userDataBean).pipe(
      __map(_r => _r.body as UserDataBean)
    );
  }

  /**
   * updateUserDataBean
   * @param userDataBean userDataBean
   * @return OK
   */
  updateUserDataBeanUsingPUTResponse(userDataBean: UserDataBean): __Observable<__StrictHttpResponse<UserDataBean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDataBean;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/user-data-beans`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDataBean>;
      })
    );
  }
  /**
   * updateUserDataBean
   * @param userDataBean userDataBean
   * @return OK
   */
  updateUserDataBeanUsingPUT(userDataBean: UserDataBean): __Observable<UserDataBean> {
    return this.updateUserDataBeanUsingPUTResponse(userDataBean).pipe(
      __map(_r => _r.body as UserDataBean)
    );
  }

  /**
   * getUserDataBean
   * @param id id
   * @return OK
   */
  getUserDataBeanUsingGETResponse(id: number): __Observable<__StrictHttpResponse<UserDataBean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user-data-beans/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDataBean>;
      })
    );
  }
  /**
   * getUserDataBean
   * @param id id
   * @return OK
   */
  getUserDataBeanUsingGET(id: number): __Observable<UserDataBean> {
    return this.getUserDataBeanUsingGETResponse(id).pipe(
      __map(_r => _r.body as UserDataBean)
    );
  }

  /**
   * deleteUserDataBean
   * @param id id
   */
  deleteUserDataBeanUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/user-data-beans/${encodeURIComponent(id)}`,
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
   * deleteUserDataBean
   * @param id id
   */
  deleteUserDataBeanUsingDELETE(id: number): __Observable<null> {
    return this.deleteUserDataBeanUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module UserDataBeanResourceService {
}

export { UserDataBeanResourceService }
