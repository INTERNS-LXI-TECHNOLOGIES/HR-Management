import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IJira, Jira } from 'app/shared/model/jira.model';
import { JiraService } from './jira.service';
import { JiraComponent } from './jira.component';
import { JiraDetailComponent } from './jira-detail.component';
import { JiraUpdateComponent } from './jira-update.component';

@Injectable({ providedIn: 'root' })
export class JiraResolve implements Resolve<IJira> {
  constructor(private service: JiraService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJira> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((jira: HttpResponse<Jira>) => {
          if (jira.body) {
            return of(jira.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Jira());
  }
}

export const jiraRoute: Routes = [
  {
    path: '',
    component: JiraComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appraisalApp.jira.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JiraDetailComponent,
    resolve: {
      jira: JiraResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appraisalApp.jira.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: JiraUpdateComponent,
    resolve: {
      jira: JiraResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appraisalApp.jira.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: JiraUpdateComponent,
    resolve: {
      jira: JiraResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appraisalApp.jira.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
