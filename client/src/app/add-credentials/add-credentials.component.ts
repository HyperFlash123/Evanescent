import { Component, OnInit } from '@angular/core';
import { CredentialsService } from '../shared';
import { Credentials } from '../shared/model/credentials-model';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-credentials',
  templateUrl: './add-credentials.component.html',
  styleUrls: ['./add-credentials.component.css'],
    providers: [CredentialsService]
})
export class AddCredentialsComponent implements OnInit {

  credentialsElt : Credentials;
  credentialsList : Array<Credentials> = [];
  display : boolean = false; 
  credentialsForm : FormGroup;
  viewsForm : FormGroup;
  formBuilder : FormBuilder;
  group : "All";
  constructor(private credentialsService: CredentialsService) { }

  ngOnInit() {
    this.credentialsForm = new FormGroup({
      userName: new FormControl(),
      passWord: new FormControl(),
      webSite: new FormControl(),
      activeStatus: new FormControl(),
      actionLink: new FormControl()
    });
    this.viewsForm = new FormGroup({
      selectView: new FormControl({value: 'All'})
    });
  }

  addNewCredentials(name : any) {
    this.credentialsService.addCredentials(JSON.stringify(name)).subscribe(credentials => this.credentialsList.push(credentials));
    this.credentialsForm.reset();
    this.getCredentials(this.group);
  }

  getCredentials(name : any) {
    console.log(name);
    if (name == null) {
      name = "All"
    }
    this.group = name;
    this.credentialsService.getAll("u1", name).subscribe(
      data => {
        this.credentialsList = data;
      },
      error => console.log(error)
    );
    
    const cred = document.getElementById("disp");
    cred.style.display = 'block';
  }
}