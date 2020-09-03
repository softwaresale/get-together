import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';
import { map, switchMap } from 'rxjs/operators';

@Injectable()
export class RequestSignerInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService
  ) {}

  /**
   * Intercepts outgoing requests. If they are API calls, the access token for the current user is appended to the
   * request
   * @param request
   * @param next
   */
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log(`URL: ${request.url}`);
    if (request.url.includes(`${environment.apiBaseUrl}/api`)) {
      console.log('URL is an API call')
      return this.authService.token$.pipe(
        switchMap(token => {
          const newRequest = request.clone({
            headers: request.headers.append('Authorization', `Bearer ${token}`)
          });
          console.log('New Request:');
          console.log(newRequest);
          return next.handle(newRequest);
        })
      );
    }
    return next.handle(request);
  }
}
