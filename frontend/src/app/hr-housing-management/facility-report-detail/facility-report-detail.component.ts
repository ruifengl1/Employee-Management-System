import { AdminHousingService } from '../../services/admin-housing.service';
import { UserStatusService } from '../../services/user-status.service';

import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-facility-report-detail',
  templateUrl: './facility-report-detail.component.html',
  styleUrls: ['./facility-report-detail.component.css']
})
export class FacilityReportDetailComponent implements OnInit{
  commentFormGroup: FormGroup;

  constructor(private http: HttpClient, private adminHousingService: AdminHousingService, private userStatusService: UserStatusService){
    this.commentFormGroup = new FormGroup({
      comment: new FormControl('', [Validators.required]),
    });
  }
  reportDetail:any=[];
  reportId:any;
  ngOnInit(): void {
    console.log(this.adminHousingService.getReportId());
    this.reportId = this.adminHousingService.getReportId();
    this.getReportDetail(this.reportId)
  }
  getReportDetail(reportId: number) {
    this.http.get('/employeeHousing-service/reports_detail/' + reportId)
      .subscribe((response) => {
        this.reportDetail = response;
        console.log(response);
      });
  }
  commentForm = {
    userId:null,
    comment:""
  };
  leaveComment(reportId:number){
    this.commentForm.userId = this.userStatusService.userStatus.userId;
    console.log(this.commentForm, reportId);
    if (!this.commentFormGroup.valid) {
      alert("The input field is required. ");
    }
    else {
      let response = this.http.post('/housing-service/facility/reports_detail/'+ reportId, this.commentForm);
      response
        .pipe(catchError((err) => of({ err })))
        .subscribe((res: any) => {
          if (res.err) {
            alert('try later');
          } else {
            alert("A new comment has been left!");
            this.getReportDetail(this.reportId);
            this.commentFormGroup.reset()
          }
        });
    }
  }
}
