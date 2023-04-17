import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserStatusService} from "../../services/user-status.service";

@Component({
  selector: 'app-onboarding-form',
  templateUrl: './onboarding-form.component.html',
  styleUrls: ['./onboarding-form.component.css']
})
export class OnboardingFormComponent implements OnInit{
  constructor(private readonly http:HttpClient, private readonly uss:UserStatusService) {
  }

  status: string = "" //the status would be INITIAL/PENDING/APPROVED/REJECTED

  ngOnInit(): void {
    const id = this.uss.userStatus.userId;

    this.http.get(`/employee-service/employee/${id}`).subscribe(data =>console.log(data))

    // TODO: use this:
    this.http.get(`/application-service/application/employee/${id}`).subscribe((data: any) =>{
      this.status = data.status;
      console.log(data.status)
    });
  }

}
