import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import * as fileSaver from 'file-saver';
import {FileService} from "../services/file.service";
import {UpdateStatusService} from '../services/updateVisaStatus.service';
import {UserStatusService} from '../services/user-status.service';
import {Observable, Subscription} from 'rxjs';

const visaMapping = new Map<string, string>([
  ["OPT Receipt", "OPT EAD"],
  ["OPT EAD", "I-983"],
  ["I-983", "I-20"],
  ["I-20", "OPT STEM Receipt"],
  ["OPT STEM Receipt", "OPT STEM EAD"],
  ["OPT STEM EAD", "null"]

])

@Component({
  selector: 'app-user-visa-management',
  templateUrl: './user-visa-management.component.html',
  styleUrls: ['./user-visa-management.component.css']
})

export class UserVisaManagementComponent implements OnInit, OnDestroy {
    visaDocuments:any = [];
    userId: number | null = null;
    file: File | null = null;
    isDisable = false;
    fetchRequestSubscription: Subscription | null = null;
    nextFile:string | undefined;
    completeMessage : string | undefined;
    totalVisas: number = 6;

  constructor(private http: HttpClient,
              private fileService: FileService,
              private updateStatusService: UpdateStatusService,
              private userStatusService: UserStatusService
  ) {}
  ngOnInit() {
    this.userId = this.userStatusService.userStatus.userId;
    // this.userId = 20;
  //   load user's info from mangodb
  this.fetchRequestSubscription = this.fetchRequest().subscribe((response) =>{
        this.visaDocuments = response;
        this.nextFile = visaMapping.get(response.at(-1).visaStatus.visaType);

    }),(error: any) => console.log('Error fetching file')

  }

  ngOnDestroy() {
    this.fetchRequestSubscription?.unsubscribe();
    // clear file
    this.file = null;
  }

  fetchRequest(): Observable<any>{
    let endpoint = `employee-service/employee/${this.userId}/visa_document`
    return this.http.get(endpoint);
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

  handleFileInput(event: any) {
    this.file = event.target.files[0]
  }
  uploadFile(visaType: string) {
    if (this.file) {
      console.log(this.file)
      // upload file
      this.fileService.uploadFile(this.file, `${this.userId}/work-authorization-documents`)
        .subscribe(response => {

          // reload data
          this.fetchRequest().subscribe((res) =>{
            this.visaDocuments = res;

          }), (error: any) => console.log('Error fetching the file');

      })

      // update status
      let type = visaMapping.get(visaType);
      if (this.userId && type) {
        this.updateStatusService.updateVisaDocument(this.userId, type, `${this.userId}/work-authorization-documents/${this.file.name}`);
      }
    } else {
      alert("Please select a file first")
    }
  }



}
