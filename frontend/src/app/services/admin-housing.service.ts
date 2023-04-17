import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminHousingService {
  houseId!: number;
  reportId!:number;

  sethouseId(houseId: number) {
    this.houseId = houseId;
  }

  gethouseId() {
    return this.houseId;
  }
  setReportId(reportId:number){
    this.reportId  = reportId;
  }
  getReportId(){
    return this.reportId;
  }
}