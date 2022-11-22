import { Component, OnInit } from '@angular/core';
import { CredentialsService } from '../shared';
import { Credentials } from '../shared/model/credentials-model';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-credentials',
  templateUrl: './add-credentials.component.html',
  styleUrls: ['./add-credentials.component.css']
})
export class AddCredentialsComponent implements OnInit {

  credentialsElt : Credentials;
  credentialsList : Array<Credentials> = [];
  display : boolean = false; 
  credentialsForm : FormGroup;
  viewsForm : FormGroup;
  formBuilder : FormBuilder;
  group : "All";
  un : string;
  pd : string;

  constructor(private credentialsService: CredentialsService) { }

  ngOnInit() {
    this.credentialsForm = new FormGroup({
      userName: new FormControl(),
      passWord: new FormControl(),
      webSite: new FormControl(),
      activeStatus: new FormControl()
    });
    this.viewsForm = new FormGroup({
      selectView: new FormControl({value: 'All'})
    });
    this.un = this.credentialsService.getUserName();
    this.pd = this.credentialsService.getPassWord();
  }

  addNewCredentials(name : any) {
    console.log(name)
    name['user'] = this.un;
    name['cpwd'] = this.pd;
    console.log(name);
    this.credentialsService.addCredentials(JSON.stringify(name)).subscribe(credentials => this.credentialsList.push(credentials));
    this.credentialsForm.reset();
    this.getCredentials(this.group);
  }

  getCredentials(name : any) {
    console.log(name);
    if (name == null) {
      name = "All"
    }
    this.un = this.credentialsService.getUserName();
    this.pd = this.credentialsService.getPassWord();
    console.log(this.un + this.pd);
    this.group = name;
    this.credentialsService.getAll(this.un, name).subscribe(
      data => {
        this.credentialsList = data;
      },
      error => console.log(error)
    );
    
    const cred = document.getElementById("disp");
    cred.style.display = 'block';
  }

  deleteCred(b : any) {
    this.credentialsService.deleteCredentials(JSON.stringify(b)).subscribe(cred => console.log(cred));
    this.credentialsService.getAll("All", name).subscribe(
      data => {
        this.credentialsList = data;
      },
      error => console.log(error)
    );
    document.getElementById("disp").innerHTML = this.getTableHTML();
    console.log(this.getTableHTML());
  }

  getTableHTML() {
    var ht = "\n\<thead \"\"\>\n\<tr \=\"\"\>\n" +
    "\<th \"\" scope\=\"col\"\>Username\<\/th\>\r\n\<th \"\" scope=\"col\"\>Password\<\/th\>\r\n" +
    "\<th \"\" scope\=\"col\"\>Website\<\/th\>\r\n\<th \"\" scope\=\"col\"\>Active\?\<\/th\>\r\n" + 
    "\<th \"\" scope=\"col\"\>Action\<\/th\>\r\n\<\/tr>\r\n\<\/thead\>\r\n" +
    "\<tbody\"\"\>\r\n\"\r\n\>";
    for (var c=0; c< this.credentialsList.length; c++){
      var cred = this.credentialsList[c];
      "\<tr\"\"\>\r\n\<td \"\"\>" + cred.userName +"\<\/td\>\r\n" +
      "\<td \"\"\>" + cred.passWord +"\<\/td\>\r\n\<td \"\"\>" + cred.webSite +"\<\/td\>\r\n" + 
      "\<td \"\"\>"+ cred.activeStatus +"\<\/td\>\r\n\<td \"\"\>\r\n" + 
      "\<button \"\" class\=\"btn btn\-danger btn\-sm px-3\" type\=\"button\"\>\<strong \"\" style\=\"color\:white\"\>x\<\/strong\>\<\/button\>\r\n" +
      "\<\/td\>\r\n\<\/tr\>\r\n\<\/tbody\>\r\n";
    }
    return ht;
  }
}