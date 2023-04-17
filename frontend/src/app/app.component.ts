import { OnInit, Component } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
    console.log('Use the following credential to test Admin:\n' + 'username:admin\n' + 'password:123');
  }
  title = 'hr-team3';
}
