import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { distinctUntilChanged, map, switchMap, take } from 'rxjs/operators';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthTokenInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return this.authService.isLogged$.pipe(
      distinctUntilChanged(),
      take(1),
      switchMap((isLogged: boolean) => {
        return isLogged ? this.appendAuthHeader$(req) : of(req);
      }),
      switchMap((request: HttpRequest<any>) => next.handle(request)),
    );
  }

  protected appendAuthHeader$(req: HttpRequest<any>): Observable<HttpRequest<any>> {
    return this.authService.userContext$.pipe(
      take(1),
      map(userContext => userContext.credentials.token),
      map((token: string) => {
        if (req.headers.has('Authorization')) {
          if (req.headers.get('Authorization') === 'none') {
            return req.clone({
              headers: req.headers.delete('Authorization'),
            });
          } else {
            return req;
          }
        } else {
          return req.clone({
            setHeaders: {
              Authorization: `Bearer ${token}`,
            },
          });
        }
      }),
    );
  }
}
