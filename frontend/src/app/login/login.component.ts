import {OnInit, Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {catchError, of} from 'rxjs';
import {UserStatusService} from '../services/user-status.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private http: HttpClient, private userStatusService: UserStatusService, private router: Router) {
  }

  ngOnInit() {

  }

  loginForm = {
    username: "admin",
    password: '123'
  };

  onSubmit() {
    let response = this.http.post('/auth/login', this.loginForm);
    response
      .pipe(catchError((err) => of({err})))
      .subscribe((res: any) => {
        if (res.err) {
          alert('username or password incorrect');
        } else {
          let body: any = res.body

          // get application status here:
          let apStatus: any
          this.http.get(`/application-service/application/employee/${parseInt(body.userId)}`).subscribe(
            (data: any) => {
              if (data && data.status) {
                apStatus = data.status;
              }
              localStorage.setItem("token", body.token);
              this.userStatusService.onStatusChange({
                isAdmin: body.isAdmin,
                isLogin: true,
                userId: body.userId,
                applicationStatus: apStatus
              });

              if (body.isAdmin) {
                this.router.navigate(['/home']);
              } else {
                this.router.navigate(['/onboarding']);
              }

            },
            (error: any) => {
              console.error('Error getting application status:', error);

              localStorage.setItem("token", body.token);
              this.userStatusService.onStatusChange({
                isAdmin: body.isAdmin,
                isLogin: true,
                userId: body.userId,
                applicationStatus: apStatus
              })

              if (body.isAdmin) {
                this.router.navigate(['/home']);
              } else {
                this.router.navigate(['/onboarding']);
              }


            }
          );

        }
      });
  }
}
