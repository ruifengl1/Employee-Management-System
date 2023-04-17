import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
const endpoint = 'employee-service/employee/all/status';

@Injectable({
  providedIn: 'root',
})
export class EmployeeStatusService {
  constructor(private http: HttpClient) {}
  getAllEmployeeStatus(): Observable<any> {
    return this.http.get(endpoint);
  }
}