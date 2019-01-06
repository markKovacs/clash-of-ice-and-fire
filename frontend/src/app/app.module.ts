import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { Store } from 'store';

// feature modules

// containers
import { AppComponent } from './containers/app/app.component';

// components

// routes
export const ROUTES: Routes = [];

// temp
export const config = {
  apiKey: "AIzaSyB0ZFS9fMi6MzJapedg5tGQPOnCcmo6ge4",
  authDomain: "clash-of-ice-and-fire.firebaseapp.com",
  databaseURL: "https://clash-of-ice-and-fire.firebaseio.com",
  projectId: "clash-of-ice-and-fire",
  storageBucket: "clash-of-ice-and-fire.appspot.com",
  messagingSenderId: "761350069334"
};

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES)
  ],
  declarations: [
    AppComponent
  ],
  providers: [
    Store
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
