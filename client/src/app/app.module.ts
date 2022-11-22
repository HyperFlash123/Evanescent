import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CredentialsListComponent } from './credentials-list/credentials-list.component';
import { AddCredentialsComponent } from './add-credentials/add-credentials.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CredentialsService } from './shared/credentials/credentials.service';

@NgModule({
  declarations: [
    AppComponent,
    CredentialsListComponent,
    AddCredentialsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [CredentialsService],
  bootstrap: [AppComponent]
})
export class AppModule { }