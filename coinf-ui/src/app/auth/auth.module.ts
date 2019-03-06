import { NgModule } from '@angular/core';
import { OAuthModule } from 'angular-oauth2-oidc';
import { HttpClientModule } from '@angular/common/http';
import { EffectsModule } from '@ngrx/effects';
import { AuthEffects } from './store/auth.effect';

@NgModule({
  imports: [
    OAuthModule.forRoot({
      resourceServer: {
        sendAccessToken: true
      }
    }),
    HttpClientModule,
    EffectsModule.forFeature([AuthEffects])
  ]
})
export class AuthModule {}
