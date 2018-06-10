import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { distinctUntilChanged, map, tap } from 'rxjs/operators';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { createApiPath } from '../utils';
import { sha256 } from 'js-sha256';

@Injectable()
export class AuthService {
  private _userContext$ = new BehaviorSubject<User>(null);

  constructor(private http: HttpClient,
              private router: Router) {
    const userFromStorage = localStorage.getItem('[fantasy]user');
    if (userFromStorage) {
      this._userContext$.next(JSON.parse(userFromStorage));
    }

    this._userContext$.subscribe(context => {
      localStorage.setItem('[fantasy]user', JSON.stringify(context));
    });
  }

  get userContext$(): Observable<User> {
    return this._userContext$.asObservable();
  }

  get isLogged$(): Observable<boolean> {
    return this._userContext$.pipe(
      map(x => !!x),
      distinctUntilChanged(),
    );
  }

  login$({username, password}: { username: string; password: string; }): Observable<{ token: string; }> {
    return this.http.post<{ token: string; }>(createApiPath('session'), {
      username,
      password: sha256(password)
    }).pipe(
      tap((res) => {
        const user: User = {credentials: {token: res.token}, username};
        this._userContext$.next(user);
      }),
      tap(() => this.router.navigateByUrl('/')),
    );
  }

  register$({username, password}: { username: string; password: string; }): Observable<any> {
    return this.http.post(createApiPath('user'), {
      username,
      password: sha256(password)
    });
  }

  logout() {
    this._userContext$.next(null);
    this.router.navigateByUrl('/login');
  }

}
