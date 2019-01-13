import { Component, OnInit } from '@angular/core';
import { OAuthService, Foo } from '../../services/oauth.service';

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
    </div>
  `
})
export class LoginComponent implements OnInit {

  private foo: Foo;

  public isLoggedIn = false;

  constructor(
    private _service: OAuthService) { }

  ngOnInit() {
    this.isLoggedIn = this._service.isLoggedIn();
  }

  login() {
    this._service.obtainAccessToken();
    this.isLoggedIn = this._service.isLoggedIn();
  }

  logout() {
    //this._service.deleteToken().subscribe((val: boolean) => console.log(val));
    this._service.logout();
    this.isLoggedIn = this._service.isLoggedIn();
  }

  getFoo() {
    const FOO_URL: string = 'http://localhost:8082/spring-security-oauth-resource/foos/';
    this._service.getResource(FOO_URL + '1')
      .subscribe(
        data => this.foo = data,
        error => console.log(error));
  }
}
