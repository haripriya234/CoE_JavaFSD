import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { Detail } from '../../model/detail';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  animalList: Detail[] = []; 
  selectedGender: string = '';

  constructor(private apiService: ApiService) { }
  
  ngOnInit(): void {
      // Fetching animal data from API
      this.apiService.getAnimals().subscribe({
          next: (result: Detail[]) => { 
              this.animalList = result; 
          },
          error: (err: any) => { 
              console.error("Error fetching animals:", err); 
          }
      });
  }
  
}
