import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  name: string = '';
  email: string = '';
  phone: string = '';
  reason: string = '';
  errors: string[] = [];

  constructor(private api: ApiService) {}

  submitForm() {
    this.errors = [];

    let emailPattern = /^[a-zA-Z0-9._]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
    let phonePattern = /^[0-9]{10}$/; // Validates a 10-digit phone number

    if (!this.name || this.name.length < 3) {
      this.errors.push("Name should be at least three characters long");
    }

    if (!this.email || !emailPattern.test(this.email)) {
      this.errors.push("Invalid Email");
    }

    if (!this.phone || !phonePattern.test(this.phone)) {
      this.errors.push("Phone number must be 10 digits long");
    }

    if (!this.reason || this.reason.length < 10) {
      this.errors.push("Message should be at least 10 characters long");
    }

    if (this.errors.length == 0) {
      this.api.addEnquiry({
        name: this.name,
        email: this.email,
        phone: this.phone,
        reason: this.reason
      }).subscribe({
        next: (response) => {
          console.log("Enquiry Form Response:", response);
          this.name = '';
          this.email = '';
          this.phone = '';
          this.reason = '';
          alert("Enquiry Submitted Successfully");
        },
        error: (error) => {
          alert("Something went wrong. Please try again");
        }
      });
    }
  }
}
