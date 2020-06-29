/* tslint:disable */
import { NgModule, ModuleWithProviders } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationInterface } from './api-configuration';

import { AccountResourceService } from './services/account-resource.service';
import { AppraisalControllerResourceService } from './services/appraisal-controller-resource.service';
import { AppraisalResourceService } from './services/appraisal-resource.service';
import { UserJwtControllerService } from './services/user-jwt-controller.service';
import { GitResourceService } from './services/git-resource.service';
import { HackathonResourceService } from './services/hackathon-resource.service';
import { JiraResourceService } from './services/jira-resource.service';
import { LateArrivalResourceService } from './services/late-arrival-resource.service';
import { LeaveResourceService } from './services/leave-resource.service';
import { ReportStatusResourceService } from './services/report-status-resource.service';
import { UserDataBeanResourceService } from './services/user-data-bean-resource.service';
import { UserExtraResourceService } from './services/user-extra-resource.service';
import { UserResourceService } from './services/user-resource.service';

/**
 * Provider for all Api services, plus ApiConfiguration
 */
@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [
    HttpClientModule
  ],
  declarations: [],
  providers: [
    ApiConfiguration,
    AccountResourceService,
    AppraisalControllerResourceService,
    AppraisalResourceService,
    UserJwtControllerService,
    GitResourceService,
    HackathonResourceService,
    JiraResourceService,
    LateArrivalResourceService,
    LeaveResourceService,
    ReportStatusResourceService,
    UserDataBeanResourceService,
    UserExtraResourceService,
    UserResourceService
  ],
})
export class ApiModule {
  static forRoot(customParams: ApiConfigurationInterface): ModuleWithProviders<ApiModule> {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: {rootUrl: customParams.rootUrl}
        }
      ]
    }
  }
}
