import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient) { }

  getAnimals():Observable<any>{
    return this.http.get('http://localhost:4500/animals');
  }
  addEnquiry(data: any): Observable<any> {
    return this.http.post('http://localhost:4500/contacts', data);
  }
  getContacts(): Observable<any> {
    return this.http.get('http://localhost:4500/contacts');
  }
  
  addContactReply(data: any): Observable<any> {
    return this.http.post('http://localhost:4500/contactresponses', data);
  }
  
  

}
