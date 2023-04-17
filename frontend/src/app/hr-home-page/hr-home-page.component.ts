import { Component, OnInit } from '@angular/core';
import { EmployeeStatusService } from '../services/employee-status.service';


interface MyDataType {
  name: string;
  workAuthorizationResponses: {
    visaType: string;
    expirationDate: Date;
    daysLeft: number;
  }[];
}

@Component({
  selector: 'app-hr-home-page',
  templateUrl: './hr-home-page.component.html',
  styleUrls: ['./hr-home-page.component.css']
})

export class HrHomePageComponent implements OnInit {
  employees: any;
  page: number = 1;
  tableSize: number = 1;
  count: number = 0;

  constructor(private employeeStatusService: EmployeeStatusService) {}

  ngOnInit():void {
    this.fetchEmployees();
  }

  fetchEmployees():void{
    this.employeeStatusService.getAllEmployeeStatus().subscribe(
      (response) => {
        this.employees = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }
  onTableDataChange(event: any) {
    this.page = event;
    this.fetchEmployees();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.fetchEmployees();
  }
}
