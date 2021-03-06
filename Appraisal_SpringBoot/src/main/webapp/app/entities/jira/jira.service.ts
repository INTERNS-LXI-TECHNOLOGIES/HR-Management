import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJira } from 'app/shared/model/jira.model';

type EntityResponseType = HttpResponse<IJira>;
type EntityArrayResponseType = HttpResponse<IJira[]>;

@Injectable({ providedIn: 'root' })
export class JiraService {
  public resourceUrl = SERVER_API_URL + 'api/jiras';

  constructor(protected http: HttpClient) {}

  create(jira: IJira): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(jira);
    return this.http
      .post<IJira>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(jira: IJira): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(jira);
    return this.http
      .put<IJira>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IJira>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IJira[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(jira: IJira): IJira {
    const copy: IJira = Object.assign({}, jira, {
      date: jira.date && jira.date.isValid() ? jira.date.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((jira: IJira) => {
        jira.date = jira.date ? moment(jira.date) : undefined;
      });
    }
    return res;
  }
}
