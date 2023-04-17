import { Component, OnInit } from '@angular/core';
import { AdminHousingService } from '../../services/admin-housing.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-facility-report',
  templateUrl: './facility-report.component.html',
  styleUrls: ['./facility-report.component.css']
})
export class FacilityReportComponent implements OnInit{
  constructor(private router: Router, private http: HttpClient, private adminHousingService: AdminHousingService){}
  reports:any=[];
  houseId:any;
  ngOnInit(): void {
    console.log(this.adminHousingService.gethouseId());
    this.houseId = this.adminHousingService.gethouseId();
    this.getReport(this.houseId)
  }
  getReport(houseId:number){
    this.http.get('employeeHousing-service/reports/house/'+houseId)
      .subscribe((response) => {
        this.reports = response;
      });
  }
  selectedStatus = '';
	onSelected(value:string): void {
		this.selectedStatus = value;
	}
  updateStatus(reportId:number){
    // console.log(reportId, this.selectedStatus);
    this.http.patch('housing-service/facility/report/'+reportId,{"status":this.selectedStatus})
    .subscribe(() => {
      alert("The report status has been updated!");
      this.getReport(this.houseId);
    });
  }
  directToFacilityPage(reportId:number){
    // console.log(reportId);
    this.adminHousingService.setReportId(reportId);
    this.router.navigate(['/hr-housing-management/facility-report/facility-report-detail']);
  }
  page: number = 1;
  tableSize: number = 5;
  count: number = 0;
  onTableDataChange(event: any) {
    this.page = event;
    this.getReport(this.houseId);
  }
}
