import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

protected currentUser :User = new User;


protected  constructor(protected authenticationService :AuthenticationService,protected http: HttpClient) {

  this.authenticationService.currentUser.subscribe( data => {
    this.currentUser = data;
  });
 }

get getHeaders():HttpHeaders{
  return new HttpHeaders(
    {
      authorization:'Bearer ' +this.currentUser?.token,
      "Content-Type":"application/json"
    }
  );
}


}
