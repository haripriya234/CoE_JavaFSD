import { Pipe, PipeTransform } from '@angular/core';
import { Detail } from '../model/detail';

@Pipe({
  name: 'filterservice'
})
export class FilterservicePipe implements PipeTransform {

  transform(petList:Detail [], selectedGender: string): Detail[] {
    if (!petList || !selectedGender || selectedGender === 'All') {
      return petList;
    }
    return petList.filter(pet => pet.gender.toLowerCase() === selectedGender.toLowerCase());
  }

}
