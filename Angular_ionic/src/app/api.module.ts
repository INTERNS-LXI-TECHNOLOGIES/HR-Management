import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { AccountResourceService } from './api/accountResource.service';
import { AppraisalControllerResourceService } from './api/appraisalControllerResource.service';
import { AppraisalResourceService } from './api/appraisalResource.service';
import { GitResourceService } from './api/gitResource.service';
import { HackathonResourceService } from './api/hackathonResource.service';
import { JiraResourceService } from './api/jiraResource.service';
import { LateArrivalResourceService } from './api/lateArrivalResource.service';
import { LeaveResourceService } from './api/leaveResource.service';
import { ReportStatusResourceService } from './api/reportStatusResource.service';
import { UserDataBeanResourceService } from './api/userDataBeanResource.service';
import { UserExtraResourceService } from './api/userExtraResource.service';
import { UserJwtControllerService } from './api/userJwtController.service';
import { UserResourceService } from './api/userResource.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: []
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders<ApiModule> {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
