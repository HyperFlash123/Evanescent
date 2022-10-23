import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CredentialsService } from './shared';
import { User } from './shared/model/user-model';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [CredentialsService]
})
export class AppComponent implements OnInit {
  title = 'Evanescent';
  authenticated = false;
  usersList :  Array<User> = [];
  loginForm : FormGroup;
  userName : string;

  constructor(private credentialsService : CredentialsService) { }

  ngOnInit() {
    this.credentialsService.getUsers().subscribe(
      data => {
        this.usersList = data;
      },
      error => console.log(error)
    );
    this.loginForm = new FormGroup({
      userName: new FormControl(),
      passWord: new FormControl()
    });
  }

  authenticate(name : any) {
    console.log(name);
    this.userName = name.userName;
    this.sendValue(this.userName);
    
    console.log(this.usersList);
    if(this.usersList.some(elt => elt.userName == name.userName && elt.passWord == name.passWord)) {
      const log1 = document.getElementById("loggingIn");
      log1.style.display = 'none';
      const log2 = document.getElementById("authenticated");
      log2.style.display = 'block';
      const log3 = document.getElementById("notAuthenticated");
      log3.style.display = 'none';
      this.loginForm.reset();
    } else {
      const log2 = document.getElementById("notAuthenticated");
      log2.style.display = 'block';
      this.loginForm.reset();
    }
  }

  sendValue(name : any){
    this.credentialsService.setUserName(name);
  }

  logout() {
    const log1 = document.getElementById("loggingIn");
    log1.style.display = 'block';
    const log2 = document.getElementById("authenticated");
    log2.style.display = 'none';
  }
}
