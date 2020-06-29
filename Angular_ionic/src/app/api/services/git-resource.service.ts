/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Git } from '../models/git';
import { GitDTO } from '../models/git-dto';

/**
 * Git Resource
 */
@Injectable({
  providedIn: 'root',
})
class GitResourceService extends __BaseService {
  static readonly getAllGitsUsingGETPath = '/api/gits';
  static readonly createGitUsingPOSTPath = '/api/gits';
  static readonly updateGitUsingPUTPath = '/api/gits';
  static readonly getGitUsingGETPath = '/api/gits/{id}';
  static readonly deleteGitUsingDELETEPath = '/api/gits/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAllGits
   * @return OK
   */
  getAllGitsUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Git>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/gits`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Git>>;
      })
    );
  }
  /**
   * getAllGits
   * @return OK
   */
  getAllGitsUsingGET(): __Observable<Array<Git>> {
    return this.getAllGitsUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Git>)
    );
  }

  /**
   * createGit
   * @param gitDTO gitDTO
   * @return OK
   */
  createGitUsingPOSTResponse(gitDTO: GitDTO): __Observable<__StrictHttpResponse<Git>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = gitDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/gits`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Git>;
      })
    );
  }
  /**
   * createGit
   * @param gitDTO gitDTO
   * @return OK
   */
  createGitUsingPOST(gitDTO: GitDTO): __Observable<Git> {
    return this.createGitUsingPOSTResponse(gitDTO).pipe(
      __map(_r => _r.body as Git)
    );
  }

  /**
   * updateGit
   * @param git git
   * @return OK
   */
  updateGitUsingPUTResponse(git: Git): __Observable<__StrictHttpResponse<Git>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = git;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/gits`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Git>;
      })
    );
  }
  /**
   * updateGit
   * @param git git
   * @return OK
   */
  updateGitUsingPUT(git: Git): __Observable<Git> {
    return this.updateGitUsingPUTResponse(git).pipe(
      __map(_r => _r.body as Git)
    );
  }

  /**
   * getGit
   * @param id id
   * @return OK
   */
  getGitUsingGETResponse(id: number): __Observable<__StrictHttpResponse<Git>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/gits/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Git>;
      })
    );
  }
  /**
   * getGit
   * @param id id
   * @return OK
   */
  getGitUsingGET(id: number): __Observable<Git> {
    return this.getGitUsingGETResponse(id).pipe(
      __map(_r => _r.body as Git)
    );
  }

  /**
   * deleteGit
   * @param id id
   */
  deleteGitUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/gits/${encodeURIComponent(id)}`,
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
   * deleteGit
   * @param id id
   */
  deleteGitUsingDELETE(id: number): __Observable<null> {
    return this.deleteGitUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module GitResourceService {
}

export { GitResourceService }
