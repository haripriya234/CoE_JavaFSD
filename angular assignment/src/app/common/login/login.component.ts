import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  login(){
 
    if(this.username=="abc" && this.password
    =="123"){
      localStorage.setItem("username",this.username)
      window.location.reload()
  }
  else alert("Invalid Credentials")
}
}
