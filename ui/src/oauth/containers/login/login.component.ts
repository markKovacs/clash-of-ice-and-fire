import { Component, OnInit } from '@angular/core';
import { OAuthService, Foo, Region } from '../../services/oauth.service';

@Component({
  selector: 'login',
  styleUrls: ['./login.component.scss'],
  template: `
    <h3>Login</h3>
    <button *ngIf="!isLoggedIn" (click)="login()">Login</button>
    <div *ngIf="isLoggedIn">
        <span>Welcome!</span>
        <a (click)="logout()">Logout</a>

        <div>
          <div>
            {{ foo | json }}
          </div>
          <button (click)="getFoo()">Get New Foo</button>
        </div>

        <div>{{ regions | json }}</div>

    </div>
  `
})
export class LoginComponent implements OnInit {

  private foo: Foo;
  private regions: Region[];

  public isLoggedIn = false;

  constructor(
    private _service: OAuthService) { }

  ngOnInit() {
    this.isLoggedIn = this._service.isLoggedIn();

    this._service.getRegions()
      .subscribe(
        data => this.regions = data,
        error => console.log(error)
      );
  }

  login() {
    this._service.obtainAccessToken();
    this.isLoggedIn = this._service.isLoggedIn();
  }

  logout() {
    this._service.logout();
    this.isLoggedIn = this._service.isLoggedIn();
  }

  getFoo() {
    this._service.getFoo(1)
      .subscribe(
        data => this.foo = data,
        error => console.log(error)
      );
  }

}