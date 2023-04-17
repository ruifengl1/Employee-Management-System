import { Component, Input} from '@angular/core';
import { Visa, defaultVisa } from 'src/app/user-profile/user-profile';
@Component({
  selector: 'app-visa-table',
  templateUrl: './visa-table.component.html',
  styleUrls: ['./visa-table.component.css']
})
export class VisaTableComponent {
  @Input() visas: Visa[] = [defaultVisa()];
  @Input() isEditing: boolean = false;
  newVisa: Visa = defaultVisa();

  addVisa() {
    if (this.newVisa.endDate == null || this.newVisa.startDate == null || this.newVisa.visaType == null) {
      alert ("All fields need to be added");
    } else {
      this.visas.push(this.newVisa);
      this.newVisa = defaultVisa();
    }
  }

  removeVisa(index: number) {
    this.visas.splice(index, 1);
  }
}