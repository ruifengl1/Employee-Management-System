export interface ReportResponse {
    status: string;
    body: any[];
  }
  export interface Facility{
    facilityId:number;
    facilityType:string;
    description:string;
    quantity:number;
  }
export interface Reports{
  author: string; 
  createDate: string;
  description: string;
  employeeId: number;
  facility:Facility;
  facilityReportId:number;
  lastModifyDate:string;
  status:string;
  title:string;
}