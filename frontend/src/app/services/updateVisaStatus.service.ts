import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UpdateStatusService {
    constructor(
        private httpClient: HttpClient,
    ) {}

updateVisaDocument(userId: number, visaType: string, path: string){
    const formData: FormData = new FormData();
    formData.append('visaType', visaType);
    formData.append('filePath', path);
    this.httpClient
    .put(
    `employee-file-service/update/${userId}/visa-document`, 
    formData,
    {responseType: 'text'}).subscribe((response) =>{
    console.log(response)
    });
}
}