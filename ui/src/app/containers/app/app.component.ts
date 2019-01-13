import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  styleUrls: ['app.component.scss'],
  template: `
    <h1>
      Clash of Ice and Fire
    </h1>

    <nav>
      <a href="/">Spring Security Oauth - Implicit Flow</a>
    </nav>

    <router-outlet></router-outlet>
  `
})
export class AppComponent { }
