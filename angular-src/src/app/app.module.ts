import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import {RouterModule , Routes} from '@angular/router';
import {AuthGuard} from './guards/auth.guard'
import {ValidateService} from './services/validate.service';
import {AuthService} from './services/auth.service';
import {ChatService} from './services/chat.service';
import {FlashMessagesModule} from 'angular2-flash-messages';
import { InternshipsComponent } from './components/internships/internships.component';
import { JobexComponent } from './components/jobex/jobex.component';
import { AboutComponent } from './components/about/about.component';

const appRoutes: Routes = [
  {path:'',component:HomeComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'dashboard',component:DashboardComponent , canActivate:[AuthGuard]},
  {path:'profile',component:ProfileComponent , canActivate:[AuthGuard]},
  {path:'internships',component:InternshipsComponent , canActivate:[AuthGuard]},
  {path:'jobex',component:JobexComponent , canActivate:[AuthGuard]},
  {path:'about',component:AboutComponent}
   
]

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    DashboardComponent,
    ProfileComponent,
    InternshipsComponent,
    JobexComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,FormsModule,HttpModule,RouterModule.forRoot(appRoutes),FlashMessagesModule
  ],
  providers: [ValidateService,AuthService,AuthGuard,ChatService],
  bootstrap: [AppComponent]
})
export class AppModule { }
