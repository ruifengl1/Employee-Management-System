import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { UserStatusService } from '../services/user-status.service';
import { catchError, of } from 'rxjs';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  isLogin: boolean = false;
  isAdmin: boolean = false;

  //for application status
  applicationStatus: string = "" //the status would be INITIAL/PENDING/APPROVED/REJECTED

  constructor(private cdr: ChangeDetectorRef, private userStatusService: UserStatusService, private readonly http:HttpClient,) {}
  ngOnInit(): void {
    // routing from admin employee profile to user profile details
    const userStatus = localStorage.getItem("userStatus")
    if(userStatus){
      this.isLogin = JSON.parse(userStatus).isLogin
      this.isAdmin = JSON.parse(userStatus).isAdmin
      this.applicationStatus = JSON.parse(userStatus).applicationStatus
      // console.log(this.applicationStatus)
    }else {
      this.isLogin = false;
      this.isAdmin = false;
      // console.log(this.applicationStatus)
    }
    this.userStatusService
    .onSubscribe()
    .pipe(catchError((err) => of({ err })))
    .subscribe((status: any) => {
      this.isLogin = status.isLogin;
      this.isAdmin = status.isAdmin;
      this.applicationStatus = status.applicationStatus;
      this.cdr.detectChanges();
    })

  }


  onLogout(): void {
    this.userStatusService.onResetStatus();
    localStorage.removeItem('token');
  }


}
