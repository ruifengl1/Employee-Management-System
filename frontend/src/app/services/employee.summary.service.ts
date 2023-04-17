import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
interface dataType {
  id: number;
  firstName: string;
  lastName: string;
  ssn: string;
  workAuthorizationTitle: string;
  phoneNumber: string;
  email: string;
}
@Injectable({
  providedIn: 'root'
})
export class EmployeeSummaryService {

  constructor(private http: HttpClient) {}

  getSummaryData():Observable<any>{
    const endpoint = "employee-service/employee/all/summary-view";
    return this.http.get(endpoint);
  }
}
