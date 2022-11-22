import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Credentials } from '../model/credentials-model';
import { Subject } from 'rxjs';

@Injectable()
  export class CredentialsService {
    private userName : any = undefined;
    private passWord : any = undefined;
    
    constructor(private http: HttpClient) {}

    getAll(un, name): Observable<any> {
      return this.http.get('http://localhost:8080/getCredentials/' + un + '/' + name);
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
      console.log("called");
      console.log(value);
    }

    public getUserName() {
      return this.userName;
    }

    public setPassWord(value : string) {
      this.passWord = value;
      console.log("called");
      console.log(value);
    }

    public getPassWord() {
      return this.passWord;
    }

    deleteCredentials(credElt : string) {
      return this.http.post<any>('http://localhost:8080/deleteCredentials/', credElt).pipe();  
    }
}