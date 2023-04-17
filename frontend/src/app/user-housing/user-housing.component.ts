import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { UserStatusService } from '../services/user-status.service';
import { catchError, of } from 'rxjs';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ReportResponse, Reports } from './user-housing';
@Component({
  selector: 'app-user-housing',
  templateUrl: './user-housing.component.html',
  styleUrls: ['./user-housing.component.css']
})
export class UserHousingComponent implements OnInit {
  housingDetails: any = [];
  reports: any = [];
  facilities: any = [];
  reportDetail: any = [];

  myForm: FormGroup;
  houseId!: number;
  cur_userId!: number;
  commentFormGroup: FormGroup;
  constructor(private cdr: ChangeDetectorRef, private http: HttpClient, private userStatusService: UserStatusService) {
    this.myForm = new FormGroup({
      facility: new FormControl('', [Validators.required]),
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required])
    });
    this.commentFormGroup = new FormGroup({
      comment: new FormControl('', [Validators.required]),
    });
    this.cur_userId = typeof this.userStatusService.userStatus.userId === 'number' ?
      this.userStatusService.userStatus.userId : 0;
  }
  ngOnInit(): void {
    this.getHousingDetails();
  }
  getHousingDetails() {
    let userId = this.userStatusService.userStatus.userId;
    // let userId = 3;
    this.http.get('employeeHousing-service/houseDetails/' + userId)
      .subscribe((response) => {
        this.housingDetails = response;
        // console.log(response);
        this.houseId = this.housingDetails.houseId;
        // this.houseId = 1234;
        console.log(this.houseId);
        if (this.houseId != null) {
          this.getFacility();
          this.getAllReport();
        }

      });
  }
  getAllReport() {
    this.reports = [];
    const parsedValue: number = typeof this.userStatusService.userStatus.userId === 'number' ?
      this.userStatusService.userStatus.userId : 0;
    this.getReportsById(parsedValue);
    // console.log(this.housingDetails.roommates);
    if (this.housingDetails.roommates) {
      for (let roommate of this.housingDetails.roommates) {
        this.getReportsById(roommate.userID)
      }
    }
  }

  getReportsById(id: number) {
    this.http.get<Reports[]>('employeeHousing-service/reports/user/' + id)
      .subscribe((response) => {
        if (response) {
          for (let r of response) {
            this.reports.push(r);
          }
        }
      });

  }
  getFacility() {
    this.http.get('/housing-service/facility/' + this.houseId)
      .subscribe((response) => {
        this.facilities = response;
        // console.log(response);
      });
  }
  reportForm = {
    userId: null,
    facilityId: "",
    title: "",
    description: ""
  };
  selectedOption: string = "";

  onSelectionChange() {
    console.log('Selected option:', this.selectedOption);
    this.reportForm.facilityId = this.selectedOption;
  }
  onSubmit() {
    this.reportForm.userId = this.userStatusService.userStatus.userId;
    // console.log(this.reportForm);
    if (!this.myForm.valid) {
      alert("Please check your report form, all field are required. ");
    }
    else {
      let response = this.http.post('/housing-service/facility/reports', this.reportForm);
      response
        .pipe(catchError((err) => of({ err })))
        .subscribe((res: any) => {
          if (res.err) {
            alert('try later');
          } else {
            let body: any = res.body;
            this.getAllReport();
            alert("A new report has been created!");
            this.myForm.reset()
          }
        });
    }
  }
  displayStyle = "none";
  facilityReportId!: number;
  myReport: boolean = false;
  openPopup(report: any) {
    this.displayStyle = "block";
    // console.log(report);
    this.facilityReportId = report.facilityReportId;
    this.getCommentDetail(report.facilityReportId);
    this.myReport = this.cur_userId == report.employeeId;
    // console.log(this.cur_userId, report.employeeId);
  }
  getCommentDetail(reportId: number) {
    this.http.get('/employeeHousing-service/reports_detail/' + reportId)
      .subscribe((response) => {
        this.reportDetail = response;
        // console.log(response);
      });
  }
  commentForm = {
    userId: null,
    comment: ""
  };
  leaveComment(reportId: number) {
    this.commentForm.userId = this.userStatusService.userStatus.userId;
    // console.log(this.commentForm, reportId);
    if (!this.commentFormGroup.valid) {
      alert("The input field is required. ");
    }
    else {
      let response = this.http.post('/housing-service/facility/reports_detail/' + reportId, this.commentForm);
      response
        .pipe(catchError((err) => of({ err })))
        .subscribe((res: any) => {
          if (res.err) {
            // alert('username or password incorrect');
          } else {

            alert("A new comment has been left!");
            this.getAllReport();
            this.commentFormGroup.reset()
          }
        });
    }
    this.closePopup();

  }
  closePopup() {
    this.displayStyle = "none";
  }


}
