import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginService } from './login/login.service'
import { SecretRequestService } from './secret-request/secret-request.service'
import { AnonymousRequestService } from './anonymous-request/anonymous-request.service'


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [LoginService,SecretRequestService,AnonymousRequestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
