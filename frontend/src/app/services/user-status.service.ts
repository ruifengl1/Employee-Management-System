import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserStatusService {

  constructor(private http: HttpClient) { }

  userStatusSubject = new Subject();
  userStatus = {
    isLogin: false,
    isAdmin: false,
    userId: null,
    applicationStatus: null
  };


  onSubscribe() {
    // Returning the Subject as an Observable for subscription
    return this.userStatusSubject.asObservable();
  }

  onLoginStatusChange(status: boolean) {
    this.userStatus.isLogin = status;
    this.userStatusSubject.next(this.userStatus);
  }

  onAdminStatusChange(status: boolean) {
    this.userStatus.isAdmin = status;
    this.userStatusSubject.next(this.userStatus);
  }

  onStatusChange(status: any) {
    this.userStatus = status;
    this.userStatusSubject.next(this.userStatus);
  }

  onResetStatus() {
    this.userStatus = {
      isLogin: false,
      isAdmin: false,
      userId: null,
      applicationStatus : null
    };
    this.userStatusSubject.next(this.userStatus);
  }
}
