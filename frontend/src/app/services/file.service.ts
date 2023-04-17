import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(
    private http: HttpClient,
  ) {
  }

  public uploadFile(file: File, filepath: string) {
    // const endpoint = 'employee-file-service/upload/visa-document';
    const endpoint = 'file-service/upload';
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('fileName', `${filepath}/${file.name}`);
    return this.http
      .post(endpoint, formData, {responseType: 'text'});
  }

  downloadFile(url:string):any {
    return this.http.get(`file-service/download/${url}`, {responseType: 'blob'});
  }

}
