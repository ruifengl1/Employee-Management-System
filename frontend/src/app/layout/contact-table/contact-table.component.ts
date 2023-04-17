import { Component, Input} from '@angular/core';
import { Contact, defaultContact } from 'src/app/user-profile/user-profile';
@Component({
  selector: 'app-contact-table',
  templateUrl: './contact-table.component.html',
  styleUrls: ['./contact-table.component.css']
})
export class ContactTableComponent {
  @Input() contacts: Contact[] = [];
  @Input() isEditing: boolean = false;
  newContact: Contact = defaultContact();

  addContact() {
    if (this.newContact.firstName == null || this.newContact.lastName == null 
      || this.newContact.cellPhone == null || this.newContact.relationship == null) {
      alert ("All fields need to be added");
    } else {
      this.contacts.push(this.newContact);
      this.newContact = defaultContact();
    }
  }

  removeContact(index: number) {
    this.contacts.splice(index, 1);
  }
}
