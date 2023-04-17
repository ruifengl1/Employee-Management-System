import { Component } from '@angular/core';
import { catchError, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserStatusService } from '../services/user-status.service';
@Component({
  selector: 'app-hr-hiring-management',
  templateUrl: './hr-hiring-management.component.html',
  styleUrls: ['./hr-hiring-management.component.css']
})
export class HrHiringManagementComponent {
  pendingApplications: any[] = [];
  rejectedApplications: any[] = [];
  approvedApplications: any[] = [];

  rejectComments: string[] = [];

  constructor(private http: HttpClient, private userStatusService: UserStatusService) {}

  ngOnInit(): void {
    this.pendingApplications = [];
    this.rejectedApplications = [];
    this.approvedApplications = [];


    this.http.get<any[]>('/employeeApplication-service/pending').subscribe((data) => {
      this.pendingApplications = data;
      console.log(this.pendingApplications);
    });

    this.http.get<any[]>('/employeeApplication-service/approved').subscribe((data) => {
      this.approvedApplications = data;
      console.log(this.approvedApplications);
    });

    this.http.get<any[]>('/employeeApplication-service/rejected').subscribe((data) => {
      this.rejectedApplications = data;
      console.log(this.rejectedApplications);
    });

  }

  approveApplication(employeeId:number) {
    const body = { status: 'APPROVED' };
    console.log(`/application-service/application/${employeeId}/status`)
    this.http.patch(`/application-service/application/${employeeId}/status`, body).subscribe(
      (res) => {
        console.log("approved application", res);
        this.http.get<any[]>('/employeeApplication-service/pending').subscribe((data) => {
          this.pendingApplications = data;
          console.log(this.pendingApplications);
        });

        this.http.get<any[]>('/employeeApplication-service/approved').subscribe((data) => {
          this.approvedApplications = data;
          console.log(this.approvedApplications);
        });
      },
      (error) => {
        this.http.get<any[]>('/employeeApplication-service/pending').subscribe((data) => {
          this.pendingApplications = data;
          console.log(this.pendingApplications);
        });

        this.http.get<any[]>('/employeeApplication-service/approved').subscribe((data) => {
          this.approvedApplications = data;
          console.log(this.approvedApplications);
        });

      }
    );
  }

  rejectApplication(userID: number, comment: string) {
    const body = {
      status: 'REJECTED',
      comment: comment
    };

    console.log(body)
    this.http.patch(`/application-service/application/${userID}`, body).subscribe(
      () => {
        // Success message
        console.log('Application rejected successfully.');

        this.http.get<any[]>('/employeeApplication-service/pending').subscribe((data) => {
          this.pendingApplications = data;
          this.rejectComments = new Array(data.length).fill('');
          console.log(this.pendingApplications);
        });

        this.http.get<any[]>('/employeeApplication-service/rejected').subscribe((data) => {
          this.rejectedApplications = data;
          console.log(this.rejectedApplications);
        });


      },
      (error) => {
        // console.error('Error rejecting application', error);

        this.http.get<any[]>('/employeeApplication-service/pending').subscribe((data) => {
          this.pendingApplications = data;
          this.rejectComments = new Array(data.length).fill('');
          console.log(this.pendingApplications);
        });

        this.http.get<any[]>('/employeeApplication-service/rejected').subscribe((data) => {
          this.rejectedApplications  = data;
          console.log(this.rejectedApplications);
        });
      }
    );
  }

  openUserProfile(userId: string){
    localStorage.setItem('userStatus', JSON.stringify({
      isAdmin: true,
      isLogin: true,
      userId: userId
    }));
    window.open('./user-profile', "_blank");
  }




  userEmail: string = 'alexyl13579@gmail.com';
  onSubmitSendEmailClick() {
    let body = {
      email: this.userEmail,
      createBy: this.userStatusService.userStatus.userId
    }
    let response = this.http.post(`/hiring-management/new-token`, body);
    response
    .pipe(catchError((err) => of({ err })))
    .subscribe((res: any) => {
      if (res.err) {
        console.log(res.err);
        alert("Send email fail.");
      } else {
        this.userEmail = '';
        alert(res.status.message);
      }
    });
  }
}
