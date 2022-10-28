import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Credentials } from '../model/credentials-model';

@Injectable()
export class CredentialsService {
  private userName : string;

  constructor(private http: HttpClient) {}

  getAll(un, name): Observable<any> {
    return this.http.get('http://localhost:8080/getCredentials/' + un + '/' + name);
  }

  deleteCredential(username, password, website): Observable<any> {
    return this.http.get('http://localhost:8080/deleteCredentials/' + username + '/' + password + '/' + website);
  }

  addCredentials(credentialsElt: string): Observable<any> {
    return this.http.post<any>('http://localhost:8080/addCredentials', credentialsElt).pipe();
  }

  getUsers(): Observable<any> {
    return this.http.get('http://localhost:8080/users');
  }

  addUser(user: string): Observable<any> {
    return this.http.post<any>('http://localhost:8080/addUsers', user).pipe();
  }

  public setUserName(value : string) {
    this.userName = value;
  }
}