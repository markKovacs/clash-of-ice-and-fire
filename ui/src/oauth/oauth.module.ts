import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OAuthModule as OAuth2Module } from 'angular-oauth2-oidc';
import { LoginComponent } from './containers/login/login.component';
import { OAuthService } from './services/oauth.service';
import { Routes, RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// routes
export const ROUTES: Routes = [
  { path: '', component: LoginComponent, pathMatch: 'full' }
];


@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(ROUTES),
    OAuth2Module.forRoot({
      resourceServer: {
        sendAccessToken: true
      }
    }),
    HttpModule,
    HttpClientModule,
    FormsModule
  ],
  exports: [],
  providers: [
    OAuthService
  ],
})
export class OAuthModule { }
