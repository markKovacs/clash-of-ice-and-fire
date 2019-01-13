import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {

  // Login Url of the auth-server
  loginUrl: 'http://localhost:8081/oauth/authorize',

  // URL of the SPA to redirect the user to after login
  redirectUri: window.location.origin + '/',

  // The SPA's id. The SPA is registerd with this id at the auth-server
  clientId: 'coinf-client-id',

  // set the scope for the permissions the client should request
  scope: 'read write foo bar',

  oidc: false
}
