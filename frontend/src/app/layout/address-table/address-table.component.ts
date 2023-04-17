import { Component, Input } from '@angular/core';
import { Address, defaultAddress } from 'src/app/user-profile/user-profile';
@Component({
  selector: 'app-address-table',
  templateUrl: './address-table.component.html',
  styleUrls: ['./address-table.component.css']
})
export class AddressTableComponent {
  @Input() addresses: Address[] = [defaultAddress()];
  @Input() isEditing: boolean = false;
  newAddress: Address = defaultAddress();

  addAddress() {
    if (this.newAddress.addressLine1 == null || this.newAddress.city == null || this.newAddress.state == null || this.newAddress.zipCode == null) {
      alert ("All fields need to be added");
    } else {
      this.addresses.push(this.newAddress);
      this.newAddress = defaultAddress();
    }
  }

  removeAddress(index: number) {
    this.addresses.splice(index, 1);
  }
}
