import {Component, OnInit} from '@angular/core';
import {Profile} from "../../user-profile/user-profile";
import {HttpClient} from "@angular/common/http";
import {UserStatusService} from "../../services/user-status.service";
import {catchError, of} from "rxjs";
import * as fileSaver from "file-saver";
import { FileService } from "../../services/file.service";

@Component({
  selector: 'app-approve-form',
  templateUrl: './approve-form.component.html',
  styleUrls: ['./approve-form.component.css']
})
export class ApproveFormComponent implements OnInit{
  profile: Profile = {
    firstName: '123',
    middleName: '',
    lastName: '',
    preferredName: '',
    email: '',
    ssn: '',
    dob: new Date(),
    gender: "male",
    addresses: [],
    contacts: [],
    personalDocuments: [],
    visaStatuses: [],
    alternatePhone: '',
    cellPhone: '',
    driverLicense: '',
    driverLicenseExpiration: '',
    id: -1,
    startDate: new Date(),
    endDate: new Date(),
    houseID: -1,
    userId: 0,
  }

  constructor(private http: HttpClient, private userStatusService: UserStatusService, private fileService: FileService) {

  }
  ngOnInit(): void {
    // let response = this.http.get(`/employee-service/employee/${this.userStatusService.userStatus.userId}`);
    let response = this.http.get(`/employee-service/employee/${this.userStatusService.userStatus.userId}`);
    response
      .pipe(catchError((err) => of({ err })))
      .subscribe((res: any) => {
        this.profile = res as Profile;
        console.log(this.profile);
      });
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

  isEditing: boolean = false;
  onEditClick() {
    this.isEditing = true;
  }
  onCancelClick() {
    this.isEditing = false;
  }
  onSaveClick() {
    this.isEditing = false;
  }

}
