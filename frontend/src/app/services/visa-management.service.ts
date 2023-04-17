import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VisaManagementService {

  constructor(private http: HttpClient) {}

  getData():Observable<any>{
    const endpoint = "employee-service/employee/all/visa-status-management";
    return this.http.get(endpoint);
  }

  approveVisaStatus(userId:number, type: string){
    const endpoint = `employee-service/employee/${userId}/visa-status-management`;
    const formData: FormData = new FormData();
    formData.append('type', type);
    formData.append('status', "APPROVED");
    return this.http.put(endpoint, formData, {responseType: 'text'});
  }

  rejectVisaStatus(userId:number, type: string){
    const endpoint = `employee-service/employee/${userId}/visa-status-management`;
    const formData: FormData = new FormData();
    formData.append('type', type);
    formData.append('status', "REJECTED");
    return this.http.put(endpoint, formData, {responseType: 'text'});
  }


}
