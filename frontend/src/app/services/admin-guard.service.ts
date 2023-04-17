import { Injectable } from '@angular/core';
import { UserStatusService } from './user-status.service';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';

@Injectable()
export class AdminGuardService implements CanActivate {
  constructor(private userStatusService: UserStatusService, private router: Router) {}

  // Information about the route and state of the router
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (!this.userStatusService.userStatus.isAdmin) {
      this.userStatusService.onResetStatus();
      this.router.navigate(['login']);
      alert('Please login as admin first!');
      return false;
    }
    return true;
  }
}
