/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { UserDTO } from '../models/user-dto';
import { Appraisal } from '../models/appraisal';
import { UserDataBean } from '../models/user-data-bean';
import { UserExtraDTO } from '../models/user-extra-dto';
import { User } from '../models/user';

/**
 * Appraisal Controller Resource
 */
@Injectable({
  providedIn: 'root',
})
class AppraisalControllerResourceService extends __BaseService {
  static readonly addUserUsingPOSTPath = '/api/appraisal-controller-resource/addUser';
  static readonly getAllUserUsingGETPath = '/api/appraisal-controller-resource/all';
  static readonly getAppraisalUsingGETPath = '/api/appraisal-controller-resource/appraisal/{id}';
  static readonly editUserUsingPOSTPath = '/api/appraisal-controller-resource/editUser';
  static readonly getPdfUsingGETPath = '/api/appraisal-controller-resource/getPdf/{id}/{start}/{end}/{joinDate}/{unSort}';
  static readonly reportByDateUsingGETPath = '/api/appraisal-controller-resource/getReportBetweenTwoDate/{start}/{end}';
  static readonly reportByDateUsingHEADPath = '/api/appraisal-controller-resource/getReportBetweenTwoDate/{start}/{end}';
  static readonly reportByDateUsingPOSTPath = '/api/appraisal-controller-resource/getReportBetweenTwoDate/{start}/{end}';
  static readonly reportByDateUsingPUTPath = '/api/appraisal-controller-resource/getReportBetweenTwoDate/{start}/{end}';
  static readonly reportByDateUsingDELETEPath = '/api/appraisal-controller-resource/getReportBetweenTwoDate/{start}/{end}';
  static readonly reportByDateUsingOPTIONSPath = '/api/appraisal-controller-resource/getReportBetweenTwoDate/{start}/{end}';
  static readonly reportByDateUsingPATCHPath = '/api/appraisal-controller-resource/getReportBetweenTwoDate/{start}/{end}';
  static readonly getUserImageUsingGETPath = '/api/appraisal-controller-resource/image/{id}';
  static readonly reportUsingGETPath = '/api/appraisal-controller-resource/report/{sort}/{start}/{end}';
  static readonly sortAppraisalUsingGETPath = '/api/appraisal-controller-resource/sortAppraisal/{id}/{start}/{end}';
  static readonly getWorkStatusUsingGETPath = '/api/appraisal-controller-resource/status/{id}';
  static readonly getUserExtraUsingGETPath = '/api/appraisal-controller-resource/user-extras/{id}';
  static readonly userNamesUsingGETPath = '/api/appraisal-controller-resource/userName';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * addUser
   * @param params The `AppraisalControllerResourceService.AddUserUsingPOSTParams` containing the following parameters:
   *
   * - `position`:
   *
   * - `password`:
   *
   * - `login`:
   *
   * - `lastName`:
   *
   * - `joiningDate`:
   *
   * - `image`:
   *
   * - `id`:
   *
   * - `firstName`:
   *
   * - `email`:
   *
   * - `dob`:
   *
   * - `company`:
   *
   * - `authorities`:
   *
   * @return OK
   */
  addUserUsingPOSTResponse(params: AppraisalControllerResourceService.AddUserUsingPOSTParams): __Observable<__StrictHttpResponse<boolean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.position != null) __params = __params.set('position', params.position.toString());
    if (params.password != null) __params = __params.set('password', params.password.toString());
    if (params.login != null) __params = __params.set('login', params.login.toString());
    if (params.lastName != null) __params = __params.set('lastName', params.lastName.toString());
    if (params.joiningDate != null) __params = __params.set('joiningDate', params.joiningDate.toString());
    if (params.image != null) __params = __params.set('image', params.image.toString());
    if (params.id != null) __params = __params.set('id', params.id.toString());
    if (params.firstName != null) __params = __params.set('firstName', params.firstName.toString());
    if (params.email != null) __params = __params.set('email', params.email.toString());
    if (params.dob != null) __params = __params.set('dob', params.dob.toString());
    if (params.company != null) __params = __params.set('company', params.company.toString());
    if (params.authorities != null) __params = __params.set('authorities', params.authorities.toString());
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/appraisal-controller-resource/addUser`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: (_r as HttpResponse<any>).body === 'true' }) as __StrictHttpResponse<boolean>
      })
    );
  }
  /**
   * addUser
   * @param params The `AppraisalControllerResourceService.AddUserUsingPOSTParams` containing the following parameters:
   *
   * - `position`:
   *
   * - `password`:
   *
   * - `login`:
   *
   * - `lastName`:
   *
   * - `joiningDate`:
   *
   * - `image`:
   *
   * - `id`:
   *
   * - `firstName`:
   *
   * - `email`:
   *
   * - `dob`:
   *
   * - `company`:
   *
   * - `authorities`:
   *
   * @return OK
   */
  addUserUsingPOST(params: AppraisalControllerResourceService.AddUserUsingPOSTParams): __Observable<boolean> {
    return this.addUserUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as boolean)
    );
  }

  /**
   * getAllUser
   * @return OK
   */
  getAllUserUsingGETResponse(): __Observable<__StrictHttpResponse<Array<UserDTO>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/all`,
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
   * getAllUser
   * @return OK
   */
  getAllUserUsingGET(): __Observable<Array<UserDTO>> {
    return this.getAllUserUsingGETResponse().pipe(
      __map(_r => _r.body as Array<UserDTO>)
    );
  }

  /**
   * getAppraisal
   * @param id id
   * @return OK
   */
  getAppraisalUsingGETResponse(id: number): __Observable<__StrictHttpResponse<Appraisal>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/appraisal/${encodeURIComponent(id)}`,
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
  getAppraisalUsingGET(id: number): __Observable<Appraisal> {
    return this.getAppraisalUsingGETResponse(id).pipe(
      __map(_r => _r.body as Appraisal)
    );
  }

  /**
   * editUser
   * @param params The `AppraisalControllerResourceService.EditUserUsingPOSTParams` containing the following parameters:
   *
   * - `position`:
   *
   * - `password`:
   *
   * - `login`:
   *
   * - `lastName`:
   *
   * - `joiningDate`:
   *
   * - `image`:
   *
   * - `id`:
   *
   * - `firstName`:
   *
   * - `email`:
   *
   * - `dob`:
   *
   * - `company`:
   *
   * - `authorities`:
   *
   * @return OK
   */
  editUserUsingPOSTResponse(params: AppraisalControllerResourceService.EditUserUsingPOSTParams): __Observable<__StrictHttpResponse<boolean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.position != null) __params = __params.set('position', params.position.toString());
    if (params.password != null) __params = __params.set('password', params.password.toString());
    if (params.login != null) __params = __params.set('login', params.login.toString());
    if (params.lastName != null) __params = __params.set('lastName', params.lastName.toString());
    if (params.joiningDate != null) __params = __params.set('joiningDate', params.joiningDate.toString());
    if (params.image != null) __params = __params.set('image', params.image.toString());
    if (params.id != null) __params = __params.set('id', params.id.toString());
    if (params.firstName != null) __params = __params.set('firstName', params.firstName.toString());
    if (params.email != null) __params = __params.set('email', params.email.toString());
    if (params.dob != null) __params = __params.set('dob', params.dob.toString());
    if (params.company != null) __params = __params.set('company', params.company.toString());
    if (params.authorities != null) __params = __params.set('authorities', params.authorities.toString());
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/appraisal-controller-resource/editUser`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: (_r as HttpResponse<any>).body === 'true' }) as __StrictHttpResponse<boolean>
      })
    );
  }
  /**
   * editUser
   * @param params The `AppraisalControllerResourceService.EditUserUsingPOSTParams` containing the following parameters:
   *
   * - `position`:
   *
   * - `password`:
   *
   * - `login`:
   *
   * - `lastName`:
   *
   * - `joiningDate`:
   *
   * - `image`:
   *
   * - `id`:
   *
   * - `firstName`:
   *
   * - `email`:
   *
   * - `dob`:
   *
   * - `company`:
   *
   * - `authorities`:
   *
   * @return OK
   */
  editUserUsingPOST(params: AppraisalControllerResourceService.EditUserUsingPOSTParams): __Observable<boolean> {
    return this.editUserUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as boolean)
    );
  }

  /**
   * getPdf
   * @param params The `AppraisalControllerResourceService.GetPdfUsingGETParams` containing the following parameters:
   *
   * - `unSort`: unSort
   *
   * - `start`: start
   *
   * - `joinDate`: joinDate
   *
   * - `id`: id
   *
   * - `end`: end
   *
   * @return OK
   */
  getPdfUsingGETResponse(params: AppraisalControllerResourceService.GetPdfUsingGETParams): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;





    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/getPdf/${encodeURIComponent(params.id)}/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}/${encodeURIComponent(params.joinDate)}/${encodeURIComponent(params.unSort)}`,
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
   * getPdf
   * @param params The `AppraisalControllerResourceService.GetPdfUsingGETParams` containing the following parameters:
   *
   * - `unSort`: unSort
   *
   * - `start`: start
   *
   * - `joinDate`: joinDate
   *
   * - `id`: id
   *
   * - `end`: end
   *
   * @return OK
   */
  getPdfUsingGET(params: AppraisalControllerResourceService.GetPdfUsingGETParams): __Observable<string> {
    return this.getPdfUsingGETResponse(params).pipe(
      __map(_r => _r.body as string)
    );
  }

  /**
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingGETParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingGETResponse(params: AppraisalControllerResourceService.ReportByDateUsingGETParams): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/getReportBetweenTwoDate/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingGETParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingGET(params: AppraisalControllerResourceService.ReportByDateUsingGETParams): __Observable<Array<UserDataBean>> {
    return this.reportByDateUsingGETResponse(params).pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingHEADParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingHEADResponse(params: AppraisalControllerResourceService.ReportByDateUsingHEADParams): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'HEAD',
      this.rootUrl + `/api/appraisal-controller-resource/getReportBetweenTwoDate/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingHEADParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingHEAD(params: AppraisalControllerResourceService.ReportByDateUsingHEADParams): __Observable<Array<UserDataBean>> {
    return this.reportByDateUsingHEADResponse(params).pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingPOSTParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingPOSTResponse(params: AppraisalControllerResourceService.ReportByDateUsingPOSTParams): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/appraisal-controller-resource/getReportBetweenTwoDate/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingPOSTParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingPOST(params: AppraisalControllerResourceService.ReportByDateUsingPOSTParams): __Observable<Array<UserDataBean>> {
    return this.reportByDateUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingPUTParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingPUTResponse(params: AppraisalControllerResourceService.ReportByDateUsingPUTParams): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/appraisal-controller-resource/getReportBetweenTwoDate/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingPUTParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingPUT(params: AppraisalControllerResourceService.ReportByDateUsingPUTParams): __Observable<Array<UserDataBean>> {
    return this.reportByDateUsingPUTResponse(params).pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingDELETEParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingDELETEResponse(params: AppraisalControllerResourceService.ReportByDateUsingDELETEParams): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/appraisal-controller-resource/getReportBetweenTwoDate/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingDELETEParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingDELETE(params: AppraisalControllerResourceService.ReportByDateUsingDELETEParams): __Observable<Array<UserDataBean>> {
    return this.reportByDateUsingDELETEResponse(params).pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingOPTIONSParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingOPTIONSResponse(params: AppraisalControllerResourceService.ReportByDateUsingOPTIONSParams): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'OPTIONS',
      this.rootUrl + `/api/appraisal-controller-resource/getReportBetweenTwoDate/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingOPTIONSParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingOPTIONS(params: AppraisalControllerResourceService.ReportByDateUsingOPTIONSParams): __Observable<Array<UserDataBean>> {
    return this.reportByDateUsingOPTIONSResponse(params).pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingPATCHParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingPATCHResponse(params: AppraisalControllerResourceService.ReportByDateUsingPATCHParams): __Observable<__StrictHttpResponse<Array<UserDataBean>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/api/appraisal-controller-resource/getReportBetweenTwoDate/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * reportByDate
   * @param params The `AppraisalControllerResourceService.ReportByDateUsingPATCHParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `end`: end
   *
   * @return OK
   */
  reportByDateUsingPATCH(params: AppraisalControllerResourceService.ReportByDateUsingPATCHParams): __Observable<Array<UserDataBean>> {
    return this.reportByDateUsingPATCHResponse(params).pipe(
      __map(_r => _r.body as Array<UserDataBean>)
    );
  }

  /**
   * getUserImage
   * @param id id
   * @return OK
   */
  getUserImageUsingGETResponse(id: number): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/image/${encodeURIComponent(id)}`,
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
   * getUserImage
   * @param id id
   * @return OK
   */
  getUserImageUsingGET(id: number): __Observable<string> {
    return this.getUserImageUsingGETResponse(id).pipe(
      __map(_r => _r.body as string)
    );
  }

  /**
   * report
   * @param params The `AppraisalControllerResourceService.ReportUsingGETParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `sort`: sort
   *
   * - `end`: end
   *
   * @return OK
   */
  reportUsingGETResponse(params: AppraisalControllerResourceService.ReportUsingGETParams): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;



    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/report/${encodeURIComponent(params.sort)}/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * report
   * @param params The `AppraisalControllerResourceService.ReportUsingGETParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `sort`: sort
   *
   * - `end`: end
   *
   * @return OK
   */
  reportUsingGET(params: AppraisalControllerResourceService.ReportUsingGETParams): __Observable<string> {
    return this.reportUsingGETResponse(params).pipe(
      __map(_r => _r.body as string)
    );
  }

  /**
   * sortAppraisal
   * @param params The `AppraisalControllerResourceService.SortAppraisalUsingGETParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `id`: id
   *
   * - `end`: end
   *
   * @return OK
   */
  sortAppraisalUsingGETResponse(params: AppraisalControllerResourceService.SortAppraisalUsingGETParams): __Observable<__StrictHttpResponse<Appraisal>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;



    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/sortAppraisal/${encodeURIComponent(params.id)}/${encodeURIComponent(params.start)}/${encodeURIComponent(params.end)}`,
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
   * sortAppraisal
   * @param params The `AppraisalControllerResourceService.SortAppraisalUsingGETParams` containing the following parameters:
   *
   * - `start`: start
   *
   * - `id`: id
   *
   * - `end`: end
   *
   * @return OK
   */
  sortAppraisalUsingGET(params: AppraisalControllerResourceService.SortAppraisalUsingGETParams): __Observable<Appraisal> {
    return this.sortAppraisalUsingGETResponse(params).pipe(
      __map(_r => _r.body as Appraisal)
    );
  }

  /**
   * getWorkStatus
   * @param id id
   * @return OK
   */
  getWorkStatusUsingGETResponse(id: number): __Observable<__StrictHttpResponse<Array<number>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/status/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<number>>;
      })
    );
  }
  /**
   * getWorkStatus
   * @param id id
   * @return OK
   */
  getWorkStatusUsingGET(id: number): __Observable<Array<number>> {
    return this.getWorkStatusUsingGETResponse(id).pipe(
      __map(_r => _r.body as Array<number>)
    );
  }

  /**
   * getUserExtra
   * @param id id
   * @return OK
   */
  getUserExtraUsingGETResponse(id: number): __Observable<__StrictHttpResponse<UserExtraDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/user-extras/${encodeURIComponent(id)}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserExtraDTO>;
      })
    );
  }
  /**
   * getUserExtra
   * @param id id
   * @return OK
   */
  getUserExtraUsingGET(id: number): __Observable<UserExtraDTO> {
    return this.getUserExtraUsingGETResponse(id).pipe(
      __map(_r => _r.body as UserExtraDTO)
    );
  }

  /**
   * userNames
   * @return OK
   */
  userNamesUsingGETResponse(): __Observable<__StrictHttpResponse<Array<User>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/appraisal-controller-resource/userName`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<User>>;
      })
    );
  }
  /**
   * userNames
   * @return OK
   */
  userNamesUsingGET(): __Observable<Array<User>> {
    return this.userNamesUsingGETResponse().pipe(
      __map(_r => _r.body as Array<User>)
    );
  }
}

module AppraisalControllerResourceService {

  /**
   * Parameters for addUserUsingPOST
   */
  export interface AddUserUsingPOSTParams {
    position?: string;
    password?: string;
    login?: string;
    lastName?: string;
    joiningDate?: string;
    image?: Blob;
    id?: number;
    firstName?: string;
    email?: string;
    dob?: string;
    company?: string;
    authorities?: string;
  }

  /**
   * Parameters for editUserUsingPOST
   */
  export interface EditUserUsingPOSTParams {
    position?: string;
    password?: string;
    login?: string;
    lastName?: string;
    joiningDate?: string;
    image?: Blob;
    id?: number;
    firstName?: string;
    email?: string;
    dob?: string;
    company?: string;
    authorities?: string;
  }

  /**
   * Parameters for getPdfUsingGET
   */
  export interface GetPdfUsingGETParams {

    /**
     * unSort
     */
    unSort: boolean;

    /**
     * start
     */
    start: string;

    /**
     * joinDate
     */
    joinDate: string;

    /**
     * id
     */
    id: number;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportByDateUsingGET
   */
  export interface ReportByDateUsingGETParams {

    /**
     * start
     */
    start: string;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportByDateUsingHEAD
   */
  export interface ReportByDateUsingHEADParams {

    /**
     * start
     */
    start: string;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportByDateUsingPOST
   */
  export interface ReportByDateUsingPOSTParams {

    /**
     * start
     */
    start: string;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportByDateUsingPUT
   */
  export interface ReportByDateUsingPUTParams {

    /**
     * start
     */
    start: string;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportByDateUsingDELETE
   */
  export interface ReportByDateUsingDELETEParams {

    /**
     * start
     */
    start: string;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportByDateUsingOPTIONS
   */
  export interface ReportByDateUsingOPTIONSParams {

    /**
     * start
     */
    start: string;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportByDateUsingPATCH
   */
  export interface ReportByDateUsingPATCHParams {

    /**
     * start
     */
    start: string;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for reportUsingGET
   */
  export interface ReportUsingGETParams {

    /**
     * start
     */
    start: string;

    /**
     * sort
     */
    sort: boolean;

    /**
     * end
     */
    end: string;
  }

  /**
   * Parameters for sortAppraisalUsingGET
   */
  export interface SortAppraisalUsingGETParams {

    /**
     * start
     */
    start: string;

    /**
     * id
     */
    id: number;

    /**
     * end
     */
    end: string;
  }
}

export { AppraisalControllerResourceService }
