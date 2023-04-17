import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HrEmployeeProfileComponent } from './hr-employee-profile/hr-employee-profile.component';
import { HrVisaManagementComponent } from './hr-visa-management/hr-visa-management.component';
import { HrHiringManagementComponent } from './hr-hiring-management/hr-hiring-management.component';
import { HrHousingManagementComponent } from './hr-housing-management/hr-housing-management.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserVisaManagementComponent } from './user-visa-management/user-visa-management.component';
import { UserHousingComponent } from './user-housing/user-housing.component';
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { RegistrationComponent } from "./registration/registration.component";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AdminGuardService } from "./services/admin-guard.service";
import { InterceptorService } from "./services/interceptor.service";
import { LoginGuardService } from "./services/login-guard.service";
import { FormattedDatePipe } from './pipe/formatted-date.pipe';
import { VisaTableComponent } from './layout/visa-table/visa-table.component';
import { AddressTableComponent } from './layout/address-table/address-table.component';
import { HrHomePageComponent } from './hr-home-page/hr-home-page.component';
import { NgxPaginationModule } from 'ngx-pagination';
import {TableFilterPipe} from "./hr-employee-profile/table-filter.pipe";


import { PendingFormComponent } from './components/pending-form/pending-form.component';
import { OnboardingFormComponent } from './components/onboarding-form/onboarding-form.component';
import { ApproveFormComponent } from './components/pending-non-editable-form/approve-form.component';
import { RejectedFormComponent } from './components/rejected-form/rejected-form.component';
import { CommonModule } from "@angular/common";
import { ContactTableComponent } from './layout/contact-table/contact-table.component';
import { ApprovedFormComponent } from './components/approved-form/approved-form.component';

import { FacilityReportDetailComponent } from './hr-housing-management/facility-report-detail/facility-report-detail.component';
import { FacilityReportComponent } from './hr-housing-management/facility-report/facility-report.component';
@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    HomeComponent,
    HrEmployeeProfileComponent,
    HrVisaManagementComponent,
    HrHiringManagementComponent,
    HrHousingManagementComponent,
    UserProfileComponent,
    UserVisaManagementComponent,
    UserHousingComponent,
    RegistrationComponent,
    FormattedDatePipe,
    VisaTableComponent,
    AddressTableComponent,
    HrHomePageComponent,
    PendingFormComponent,
    OnboardingFormComponent,
    ApproveFormComponent,
    RejectedFormComponent,
    ContactTableComponent,
    TableFilterPipe,
    ApprovedFormComponent,
    HrHomePageComponent,
    FacilityReportComponent,
    FacilityReportDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    CommonModule,
  ],
  providers: [AdminGuardService, LoginGuardService,
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
