/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { UserDTO } from '../models/user-dto';
import { User } from '../models/user';

/**
 * User Resource
 */
@Injectable({
  providedIn: 'root',
})
class UserResourceService extends __BaseService {
  static readonly getAllUsersUsingGETPath = '/api/users';
  static readonly createUserUsingPOSTPath = '/api/users';
  static readonly updateUserUsingPUTPath = '/api/users';
  static readonly getAuthoritiesUsingGETPath = '/api/users/authorities';
  static readonly getUserUsingGET1Path = '/api/users/{login}';
  static readonly deleteUserUsingDELETEPath = '/api/users/{login}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllUsers
   * @param params The `UserResourceService.GetAllUsersUsingGETParams` containing the following parameters:
   *
   * - `sort`: Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
   *
   * - `size`: Size of a page
   *
   * - `page`: Page number of the requested page
   *
   * @return OK
   */
  getAllUsersUsingGETResponse(params: UserResourceService.GetAllUsersUsingGETParams): __Observable<__StrictHttpResponse<Array<UserDTO>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    (params.sort || []).forEach(val => {if (val != null) __params = __params.append('sort', val.toString())});
    if (params.size != null) __params = __params.set('size', params.size.toString());
    if (params.page != null) __params = __params.set('page', params.page.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/users`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<UserDTO>>;
      })
    );
  }
  /**
   * getAllUsers
   * @param params The `UserResourceService.GetAllUsersUsingGETParams` containing the following parameters:
   *
   * - `sort`: Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
   *
   * - `size`: Size of a page
   *
   * - `page`: Page number of the requested page
   *
   * @return OK
   */
  getAllUsersUsingGET(params: UserResourceService.GetAllUsersUsingGETParams): __Observable<Array<UserDTO>> {
    return this.getAllUsersUsingGETResponse(params).pipe(
      __map(_r => _r.body as Array<UserDTO>)
    );
  }

  /**
   * createUser
   * @param userDTO userDTO
   * @return OK
   */
  createUserUsingPOSTResponse(userDTO: UserDTO): __Observable<__StrictHttpResponse<User>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/users`,
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
   * createUser
   * @param userDTO userDTO
   * @return OK
   */
  createUserUsingPOST(userDTO: UserDTO): __Observable<User> {
    return this.createUserUsingPOSTResponse(userDTO).pipe(
      __map(_r => _r.body as User)
    );
  }

  /**
   * updateUser
   * @param userDTO userDTO
   * @return OK
   */
  updateUserUsingPUTResponse(userDTO: UserDTO): __Observable<__StrictHttpResponse<UserDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDTO;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/users`,
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
   * updateUser
   * @param userDTO userDTO
   * @return OK
   */
  updateUserUsingPUT(userDTO: UserDTO): __Observable<UserDTO> {
    return this.updateUserUsingPUTResponse(userDTO).pipe(
      __map(_r => _r.body as UserDTO)
    );
  }

  /**
   * getAuthorities
   * @return OK
   */
  getAuthoritiesUsingGETResponse(): __Observable<__StrictHttpResponse<Array<string>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/users/authorities`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<string>>;
      })
    );
  }
  /**
   * getAuthorities
   * @return OK
   */
  getAuthoritiesUsingGET(): __Observable<Array<string>> {
    return this.getAuthoritiesUsingGETResponse().pipe(
      __map(_r => _r.body as Array<string>)
    );
  }

  /**
   * getUser
   * @param login login
   * @return OK
   */
  getUserUsingGET1Response(login: string): __Observable<__StrictHttpResponse<UserDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/users/${encodeURIComponent(login)}`,
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
   * getUser
   * @param login login
   * @return OK
   */
  getUserUsingGET1(login: string): __Observable<UserDTO> {
    return this.getUserUsingGET1Response(login).pipe(
      __map(_r => _r.body as UserDTO)
    );
  }

  /**
   * deleteUser
   * @param login login
   */
  deleteUserUsingDELETEResponse(login: string): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/users/${encodeURIComponent(login)}`,
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
   * deleteUser
   * @param login login
   */
  deleteUserUsingDELETE(login: string): __Observable<null> {
    return this.deleteUserUsingDELETEResponse(login).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module UserResourceService {

  /**
   * Parameters for getAllUsersUsingGET
   */
  export interface GetAllUsersUsingGETParams {

    /**
     * Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
     */
    sort?: Array<string>;

    /**
     * Size of a page
     */
    size?: number;

    /**
     * Page number of the requested page
     */
    page?: number;
  }
}

export { UserResourceService }
