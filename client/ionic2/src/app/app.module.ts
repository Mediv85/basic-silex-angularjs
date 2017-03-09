import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { AnonymousRequestService } from '../pages/anonymous-request/anonymous-request.service';
import { LoginService } from '../pages/login/login.service';
import { SecretRequestService } from '../pages/secret-request/secret-request.service';

@NgModule({
  declarations: [
    MyApp,
    HomePage
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler},AnonymousRequestService,SecretRequestService,LoginService]
})
export class AppModule {}
