import { Component, Input } from '@angular/core';
import { Detail } from '../../../model/detail';

@Component({
  selector: 'app-detail-item',
  templateUrl: './detail-item.component.html',
  styleUrl: './detail-item.component.css'
})
export class DetailItemComponent {
  @Input() animal: Detail = {
    id: '',
    breedName: '',
    age: 0,
    color: '',
    healthCondition: '',
    dob: '',
    gender: '',
    weight: '',
    adoptionStatus: '',
    image: ''
  };
  selectedGender:string='All';
}
