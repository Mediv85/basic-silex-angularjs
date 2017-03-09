import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';
import { AnonymousRequestService } from '../anonymous-request/anonymous-request.service';
import { LoginService } from '../login/login.service';
import { LoginAuth } from '../login/login';
import { SecretRequestService } from '../secret-request/secret-request.service';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  locations: Array<String>;
  selectedLocation: String;
  loginCredentials:LoginAuth= new LoginAuth();
  token:String;
  secret:String;
  anonymous:String;

  constructor(public navCtrl: NavController, private anonymousRequestService: AnonymousRequestService, private loginService: LoginService, private secretRequestService:SecretRequestService) {
    this.locations = new Array<String>();
        this.locations.push("http://localhost:8080/");
        this.locations.push("http://localhost:8888/");

        this.selectedLocation=this.locations[0];
  }

  login(){
    this.loginService.authenticate(this.selectedLocation,this.loginCredentials)
      .subscribe(
      res => {
        this.token=res.token ;
        localStorage.setItem('currentUser', JSON.stringify({ username: res.username, token: res.token }));
      },
      error => console.log(<any>error)
      );
  }

  tellMeASecret(){
    this.secretRequestService.tellMeASecret(this.selectedLocation, this.token)
    .subscribe(
      res =>{

        this.secret=res.responce;
      },
      error => console.log(<any>error)
    )
  }

  anonymousCall(){
    this.anonymousRequestService.anonymousCall(this.selectedLocation)
    .subscribe(
      res =>{

        this.anonymous=res.responce;
      },
      error => console.log(<any>error)
    )

  }


  resetAuth(){
    this.token=null;
    this.secret=null;
    this.anonymous=null;
  }

  onLocationChange(){
    this.resetAuth();
  }

}
