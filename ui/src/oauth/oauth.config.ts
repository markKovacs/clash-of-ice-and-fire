import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {

  // Login Url of the authorization server
  loginUrl: 'http://localhost:8081/oauth/authorize',

  // URL of the SPA to redirect the user to after login
  redirectUri: window.location.origin + '/',

  // This SPA is registerd with this id at the authorization server
  clientId: 'coinf-client-id',

  // Scope for the permissions the client should request
  scope: 'read write foo bar',

  // Enalbe-disable Open ID Connect
  oidc: false
}
