import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-contact-response',
  templateUrl: './contact-response.component.html',
  styleUrl: './contact-response.component.css'
})
export class ContactResponseComponent {
  ids: string[] = [];
  emailMap: { [key: string]: string } = {}; // Store email mapped by Contact ID
  contactResponseForm: FormGroup;

  constructor(private fb: FormBuilder, private api: ApiService) {
    this.contactResponseForm = this.fb.group({
      contactId: ['', Validators.required], // Dropdown selection
      email: [{ value: '', disabled: true }, Validators.required], // Auto-filled email
      subject: ['', Validators.required],
      message: ['', Validators.required],
      respondedAt: [new Date().toISOString()] // Auto-filled response date
    });
  }

  ngOnInit() {
    // Fetch contacts from API
    this.api.getContacts().subscribe({
      next: (result: any) => {
        this.ids = result.map((contact: any) => contact.id);
        this.emailMap = result.reduce((acc: any, contact: any) => {
          acc[contact.id] = contact.email; // Mapping ID to email
          return acc;
        }, {});
      },
      error: (error: any) => console.error('Error fetching contacts:', error)
    });
  }

  // Update email when Contact ID is selected
  onIdChange(event: any) {
    const selectedId = event.target.value;
    this.contactResponseForm.patchValue({ email: this.emailMap[selectedId] || '' });
  }

  // Submit the response
  onSubmit() {
    if (this.contactResponseForm.valid) {
      this.api.addContactReply(this.contactResponseForm.getRawValue()).subscribe({
        next: (response: any) => {
          console.log('Response submitted successfully', response);
          alert('Response submitted successfully');
          this.contactResponseForm.reset();
        },
        error: (error: any) => {
          console.error('Error submitting response:', error);
          alert('Something went wrong. Please try again.');
        }
      });
    }
  }
}
