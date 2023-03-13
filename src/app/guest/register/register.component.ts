import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  user :User = new User();
  errorMessage:string = "";
  constructor(private authenticationService :AuthenticationService,private router:Router) { }

  ngOnInit(): void {


    if(this.authenticationService.currentUserValue?.id){
      this.router.navigate(['/profile']);
      return;
    }
  }


  register(){
    this.authenticationService.register(this.user).subscribe({next : data => {
      this.router.navigate(['/login']);
    },error:err =>{
      if(err?.status === 409){
        this.errorMessage = "username already exist";
      }else{
        this.errorMessage = "unexpected error occured error is " +err?.errorMessage;
        console.log(err);
      }
    }
  })
  }
}
