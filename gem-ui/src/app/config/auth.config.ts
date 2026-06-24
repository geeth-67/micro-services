import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  issuer: 'http://localhost:9001/realms/gem-trading',
    clientId: 'angular-client',
    redirectUri: 'http://localhost:4200',
    responseType: 'code',
    scope: 'openid',
    // Enabling pkce(Proof Key for Code Exchange)
    useSilentRefresh: true,
    // set to true for producation
    requireHttps: false,
};