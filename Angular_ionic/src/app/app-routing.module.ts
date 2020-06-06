
import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: '',
    redirectTo: 'folder/Inbox',
    pathMatch: 'full'
  },
  // {
  //   path: '',
  //   redirectTo: '/user-detail',
  //   pathMatch: 'full'
  // },
  {
    path: 'folder/:id',
    loadChildren: () => import('./folder/folder.module').then( m => m.FolderPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'user-detail',
    loadChildren: () => import('./pages/user-detail/user-detail.module').then( m => m.UserDetailPageModule)
  },
  {
    path: 'leave',
    loadChildren: () => import('./pages/leave/leave.module').then( m => m.LeavePageModule)
  },
  {
    path: 'evaluation',
    loadChildren: () => import('./Pages/evaluation/evaluation.module').then( m => m.EvaluationPageModule)
  },
  {
    path: 'adduser',
    loadChildren: () => import('./Pages/adduser/adduser.module').then( m => m.AdduserPageModule)
  },  {
    path: 'appraisal-details',
    loadChildren: () => import('./Pages/appraisal-details/appraisal-details.module').then( m => m.AppraisalDetailsPageModule)
  },
  {
    path: 'work-profile',
    loadChildren: () => import('./Pages/work-profile/work-profile.module').then( m => m.WorkProfilePageModule)
  },
<<<<<<< HEAD
=======
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'user-detail',
    loadChildren: () => import('./pages/user-detail/user-detail.module').then( m => m.UserDetailPageModule)
  },
  {
    path: 'leave',
    loadChildren: () => import('./pages/leave/leave.module').then( m => m.LeavePageModule)
  },
  {
    path: 'evaluation',
    loadChildren: () => import('./Pages/evaluation/evaluation.module').then( m => m.EvaluationPageModule)
  },
  {
    path: 'adduser',
    loadChildren: () => import('./Pages/adduser/adduser.module').then( m => m.AdduserPageModule)
  },
  {
    path: 'late-arrival',
    loadChildren: () => import('./Pages/late-arrival/late-arrival.module').then( m => m.LateArrivalPageModule)
  },
  {
    path: 'report-status',
    loadChildren: () => import('./Pages/report-status/report-status.module').then( m => m.ReportStatusPageModule)
  },




];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
>>>>>>> d691ae1195a960336fda1927cc59ade9f42d2207




];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}

