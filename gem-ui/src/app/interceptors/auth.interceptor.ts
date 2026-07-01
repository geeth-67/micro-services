import{HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";


export const authInterceptor: HttpInterceptorFn = (req, next) => {
    const authService = inject(OAuthService);
    const accessToken = authService.getAccessToken();

    if (req.url.includes('/api') && accessToken) {
        const clonedRequest = req.clone({
            setHeaders: {
                Authorization: `Bearer ${accessToken}`
            }
        });
        return next(clonedRequest);
    }

    return next(req);
}