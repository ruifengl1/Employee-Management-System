import { Component } from '@angular/core';
import {EmployeeSummaryService} from "../services/employee.summary.service";
import { Data } from './hr-employee-profile';

@Component({
  selector: 'app-hr-employee-profile',
  templateUrl: './hr-employee-profile.component.html',
  styleUrls: ['./hr-employee-profile.component.css']
})
export class HrEmployeeProfileComponent {
  jsonData: Data[] = [];
  page: number = 1;
  tableSize: number = 10;
  count: number = 0;
  filter = {};

  fields:any= {
    firstName: '',
    lastName: '',
    email: ''
  };

  constructor(private employeeSummaryService:EmployeeSummaryService) {}

  ngOnInit() {
    this.fetchSummaryData();
  }

  fetchSummaryData():void{
    this.employeeSummaryService.getSummaryData().subscribe(
      (response) => {
        this.jsonData = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.fetchSummaryData();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.fetchSummaryData();
  }

  updateFilters() {
    Object.keys(this.fields).forEach(key => this.fields[key] === '' ? delete this.fields[key] : key);
    this.filter = Object.assign({}, this.fields);
  }

  openUserProfile(userId: string){
    localStorage.setItem('userStatus', JSON.stringify({
      isAdmin: true,
      isLogin: true,
      userId: userId
    }));
    window.open('./user-profile', "_blank");
  }

}
