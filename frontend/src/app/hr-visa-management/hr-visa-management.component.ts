import { Component } from '@angular/core';
import {VisaManagementService} from "../services/visa-management.service";

@Component({
  selector: 'app-hr-visa-management',
  templateUrl: './hr-visa-management.component.html',
  styleUrls: ['./hr-visa-management.component.css']
})
export class HrVisaManagementComponent {
  jsonData: any;
  page: number = 1;
  tableSize: number = 1;
  isDisabled = false;

  constructor(private visaManagementService: VisaManagementService) {
  }

  count: number = 0;

  ngOnInit() {
    this.fetchData();
  }

  fetchData():void{
    this.visaManagementService.getData().subscribe(
      (response) => {
        this.jsonData = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  approveAction(userId:number, type:string){
    this.visaManagementService.approveVisaStatus(userId, type).subscribe(
      (response) => {
        console.log(response)
        this.fetchData();
        if(response){
          this.isDisabled = true
        }
      },
      (error) => {
        console.log(error);
      }
    )
  }

  rejectAction(userId:number, type:string){
    this.visaManagementService.rejectVisaStatus(userId, type).subscribe(
      (response) => {
        this.fetchData();
        if(response){
          this.isDisabled = true
        }
      },
      (error) => {
        console.log(error);
      }
    )
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.fetchData();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.fetchData();
  }

}
