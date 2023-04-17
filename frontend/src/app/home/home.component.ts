import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private http: HttpClient) {
    
  }
  test() {
    let response = this.http.get('/auth/user');
    response
    .subscribe((res: any) => {
      console.log(res)
    })
  }
}
