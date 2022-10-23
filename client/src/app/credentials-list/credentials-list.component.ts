import { Component, OnInit } from '@angular/core';
import { CredentialsService } from '../shared';

@Component({
  selector: 'app-Credentials-list',
  templateUrl: './Credentials-list.component.html',
  styleUrls: ['./Credentials-list.component.css'],
  providers: [CredentialsService]
})

export class CredentialsListComponent implements OnInit {
  credentialsList: Array<any>;

  constructor(private credentialsService: CredentialsService) { }

  ngOnInit() {
    this.credentialsService.getAll("u1", "all").subscribe(
      data => {
        this.credentialsList = data;
      },
      error => console.log(error)
    )
  }
}
