import { Injectable } from '@angular/core';
import { UserStatusService } from './user-status.service';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';

@Injectable()
export class LoginGuardService implements CanActivate {
  constructor(private userStatusService: UserStatusService, private router: Router) {
    let tempStatus = userStatusService.userStatus;
    const statusJson = localStorage.getItem("userStatus");
    this.userStatusService.userStatus = statusJson !== null? JSON.parse(statusJson) : tempStatus;

    localStorage.removeItem('userStatus');
  }


  // Information about the route and state of the router
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (!this.userStatusService.userStatus.isLogin) {
      this.userStatusService.onResetStatus();
      this.router.navigate(['login']);
      alert('Please login first!');
      return false;
    }
    return true;
  }
}
