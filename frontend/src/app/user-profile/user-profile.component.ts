import { OnInit, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, of } from 'rxjs';
import { UserStatusService } from '../services/user-status.service';
import { FileService } from "../services/file.service";
import { Profile, defaultProfile } from './user-profile';
import * as fileSaver from 'file-saver';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  profilePicture: File | null = null;
  profile: Profile = defaultProfile();
  profileBeforeEdit: Profile = this.profile;
  constructor(private http: HttpClient,
    private userStatusService: UserStatusService,
    private fileService: FileService) {

  }
  ngOnInit(): void {
    //let response = this.http.get(`/employee-service/employee/87`);
    let response = this.http.get(`/employee-service/employee/${this.userStatusService.userStatus.userId}`);
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
    this.isEditing = false;
    this.patchEmployee();
    //console.log(this.profile);
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
      console.log(this.profilePicture);
      let path = `${this.userStatusService.userStatus.userId}/application-documents`
      this.fileService.uploadFile(this.profilePicture, path)
        .subscribe(response => {
          console.log(response)
      })
    } else {
      alert("Please select a file first")
    }
  }
  handleFileInput(event: any) {
    this.profilePicture = event.target.files[0];
  }
  patchEmployee() {
    let response = this.http.patch(`/employee-service/employee/${this.userStatusService.userStatus.userId}`, this.profile);
    response
    .pipe(catchError((err) => of({ err })))
    .subscribe((res: any) => {
      //this.profile = res as Profile;
      console.log(res);
      console.log("Update:" + this.profile);
    });
  }
  patchContact() {
    let response = this.http.patch(`/employee-service/employee/${this.userStatusService.userStatus.userId}/contacts`
    , this.profile.contacts);
    response.subscribe((res: any) => {
      console.log(res);
    });
  }
  patchAddress() {
    let response = this.http.patch(`/employee-service/employee/${this.userStatusService.userStatus.userId}/addresses`
    , this.profile.addresses);
    response.subscribe((res: any) => {
      console.log(res);
    });
  }
}
