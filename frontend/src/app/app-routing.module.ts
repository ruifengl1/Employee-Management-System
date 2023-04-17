import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HrEmployeeProfileComponent } from './hr-employee-profile/hr-employee-profile.component';
import { HrHiringManagementComponent } from './hr-hiring-management/hr-hiring-management.component';
import { HrHousingManagementComponent } from './hr-housing-management/hr-housing-management.component';
import { HrVisaManagementComponent } from './hr-visa-management/hr-visa-management.component';
import { UserHousingComponent } from './user-housing/user-housing.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserVisaManagementComponent } from './user-visa-management/user-visa-management.component';
import { AdminGuardService } from './services/admin-guard.service';
import { LoginGuardService } from './services/login-guard.service';
import {OnboardingFormComponent} from "./components/onboarding-form/onboarding-form.component";

import { FacilityReportComponent } from './hr-housing-management/facility-report/facility-report.component';
import { FacilityReportDetailComponent } from './hr-housing-management/facility-report-detail/facility-report-detail.component';
const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegistrationComponent },
  { path: 'home', component: HomeComponent, canActivate: [LoginGuardService] },
  { path: 'hr-employee-profile', component: HrEmployeeProfileComponent, canActivate: [AdminGuardService] },
  { path: 'hr-hiring-management', component: HrHiringManagementComponent, canActivate: [AdminGuardService] },
  { path: 'hr-housing-management', component: HrHousingManagementComponent, canActivate: [AdminGuardService] },
  { path: 'hr-housing-management/facility-report', component: FacilityReportComponent, canActivate: [AdminGuardService] },
  { path: 'hr-housing-management/facility-report/facility-report-detail', component: FacilityReportDetailComponent, canActivate: [AdminGuardService] },
  { path: 'hr-visa-management', component: HrVisaManagementComponent, canActivate: [AdminGuardService] },
  { path: 'user-profile', component: UserProfileComponent, canActivate: [LoginGuardService] },
  { path: 'user-housing', component: UserHousingComponent, canActivate: [LoginGuardService] },
  { path: 'user-visa-management', component: UserVisaManagementComponent, canActivate: [LoginGuardService]},
  { path: 'onboarding', component: OnboardingFormComponent, canActivate: [LoginGuardService]},
  { path: '**', redirectTo: 'login' } // redirect to home if route not found
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
