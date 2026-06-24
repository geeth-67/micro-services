import {inject, Injectable} from '@angular/core';
import {OAuthService} from 'angular-oauth2-oidc';
import {authConfig} from '../config/auth.config';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private oauthService = inject(OAuthService);
  private initialized = false;

  constructor() {
    this.oauthService.configure(authConfig);
  }

  public async initAuth(): Promise<void> {
    if (this.initialized) return;

    try {
      await this.oauthService.loadDiscoveryDocumentAndLogin();
      this.initialized = true;

      if (!this.oauthService.hasValidAccessToken()) {
        this.oauthService.setupAutomaticSilentRefresh();
      }
    } catch (error) {
      console.error('Error initializing auth service', error);
    }
  }

  public login(): void {
    this.oauthService.initCodeFlow();
  }

  public logout(): void {
    this.oauthService.logOut();

  }
  // oauthService.isAuthenticated
  get isAuthenticated(): boolean {
    return this.oauthService.hasValidAccessToken();
  }
  get accessToken(): string | null {
    return this.oauthService.getAccessToken();
  }
  get userClaims(): any {
    return this.oauthService.getIdentityClaims();
  }
  get grantedScopes(): string[] {
    const scopes: any = this.oauthService.getGrantedScopes();
    return scopes ? scopes.split(' ') : [];
  }
  get tokenExpirationDate(): Date | null {
    const expiration = this.oauthService.getAccessTokenExpiration();
    return expiration ? new Date(expiration) : null;
  }
  refreshToken(): Promise<object> {
    return this.oauthService.refreshToken();
  }
}