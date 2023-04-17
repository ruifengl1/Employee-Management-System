import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserStatusService} from "../../services/user-status.service";
import {catchError, of} from "rxjs";
import {defaultProfile, Profile} from "../../user-profile/user-profile";
import * as fileSaver from "file-saver";
import {FileService} from "../../services/file.service";
import {Router} from "@angular/router";
interface EmployeeApplication {
  id: number;
  employeeId: number;
  createDate: Date | null;
  lastModificationDate: Date | null;
  status: string;
  comment: string;
}
@Component({
  selector: 'app-rejected-form',
  templateUrl: './rejected-form.component.html',
  styleUrls: ['./rejected-form.component.css']
})


export class RejectedFormComponent implements OnInit{
  profilePicture: File | null = null;
  profile: Profile = defaultProfile();
  profileBeforeEdit: Profile = this.profile;
  constructor(private http: HttpClient,
              private userStatusService: UserStatusService,
              private fileService: FileService,
              private router: Router) {

  }


  comment: any;

  ngOnInit(): void {



    const id = this.userStatusService.userStatus.userId;
    this.http.get<EmployeeApplication>(`/application-service/application/employee/${id}/`).subscribe(data =>{
      this.comment = data.comment;
    });

    this.http.get(`/application-service/application/employee/${id}/`).subscribe(data =>console.log(data))



    let response = this.http.get(`/employee-service/employee/${id}`);
    //let response = this.http.get(`/employee-service/employee/${this.userStatusService.userStatus.userId}`);
    response
      .pipe(catchError((err) => of({ err })))
      .subscribe((res: any) => {
        this.profile = res as Profile;
        console.log(this.profile);
      });
  }
  isEditing: boolean = false;
  onEditClick() {
    this.isEditing = true;
    this.profileBeforeEdit = Object.assign({}, this.profile);
  }
  onCancelClick() {
    this.isEditing = false;
    this.profile = Object.assign({}, this.profileBeforeEdit);
  }
  onSaveClick() {
    const id = this.userStatusService.userStatus.userId;
    this.isEditing = false;
    console.log(this.profile);

    // this.http.patch(`/employee-service/employee/${id}`, this.profile)
    this.http.patch(`/employee-service/employee/999`, this.profile)
      .subscribe(
        (response) => {
          console.log("patch employee successfully", response);
        },
        (error) => {
          console.error("Error when patch employee", error);
        }
      );

    const status = {
      status: "PENDING",
    };

    //change application status to PENDING
    this.http.patch(`/application-service/application/${id}/status`, status)
      .subscribe(
        (response) => {
          console.log("change status", response);
        },
        (error) => {
          console.error("Error when change status", error);
        }
      );

    //redirect to user-visa
    this.router.navigate(['/user-visa-management']);
  }

  downloadFile(url:string) {
    let filename = url.split('/').at(-1);
    this.fileService.downloadFile(url).subscribe((response: any) => {
      let blob:any = new Blob([response], { type: 'text/json; charset=utf-8' });
      const url = window.URL.createObjectURL(blob);
      fileSaver.saveAs(blob, filename);
    }), (error: any) => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  uploadFile() {
    if (this.profilePicture) {
      this.fileService.uploadFile(this.profilePicture, "profile")
        .subscribe(response => {
          console.log(response)
        })
    } else {
      alert("Please select a file first")
    }
  }


}
