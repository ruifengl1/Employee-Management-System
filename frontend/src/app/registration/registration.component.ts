import { OnInit, Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  isSubmitButtonDisabled = true;
  signupForm = {
    username: 'user2',
    password: '123',
    email: '',
    tokenId: null
  };
  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) {}
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const token = params.get('token');
      if (!token) {
        this.handleTokenError();
      } else {
        console.log(token);
        let response = this.http.get(`/auth/registration-token/token-name/${token}`);
        response
        .pipe(catchError((err) => of({ err })))
        .subscribe((res: any) => {
          if (!res.body) {
            this.handleTokenError();
          } else{
            this.isSubmitButtonDisabled = false;
            let body: any = res.body;
            const currentDateTime = new Date();
            const expirationDateTime = new Date(body.expirationDate);
            if (currentDateTime > expirationDateTime || body.consumed) {
              this.handleTokenError();
            } else {
              this.signupForm.email = body.email;
              this.signupForm.tokenId = body.registrationTokenId;
            }
          }
        });
      }
    });
  }



  onSubmit() {
    let response = this.http.post('/auth/signup', this.signupForm);
    this.isSubmitButtonDisabled = true;
    response
    .pipe(catchError((err) => of({ err })))
    .subscribe((res: any) => {
      this.isSubmitButtonDisabled = false;
      if (res.err) {
        alert('username or email already exists');
      } else {

        //post a new application
        const application = {
          employeeId: `${res.body.userId}`,
          status: "INITIAL",
        };

        this.http.post(`/application-service/application/`, application)
          .subscribe(
            (response) => {
              console.log("add successfully", response);
            },
            (error) => {
              console.error("Error when add application", error);
            }
          );


        alert(`Signup success!! ${res.body.username}`)
        this.router.navigate(['login']);
      }
    });
  }
  handleTokenError() {
    this.router.navigate(['login']);
    alert('token does not exist, expired or consumed.');
  }

}
