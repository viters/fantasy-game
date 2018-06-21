import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { distinctUntilChanged, filter, map, tap } from 'rxjs/operators';
import { Context } from 'src/app/models/context';
import { Router } from '@angular/router';
import { createApiPath } from '../utils';
import { sha256 } from 'js-sha256';
import * as jwtDecode from 'jwt-decode';

const SessionsPath = 'sessions';
const UsersPath = 'users';

@Injectable()
export class AuthService {
  private _userContext$ = new BehaviorSubject<Context>(null);

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

  get userContext$(): Observable<Context> {
    return this._userContext$.pipe(filter(Boolean));
  }

  get isLogged$(): Observable<boolean> {
    return this._userContext$.pipe(
      map(x => !!x),
      distinctUntilChanged(),
    );
  }

  get isAdmin$(): Observable<boolean> {
    return this._userContext$.pipe(
      filter(Boolean),
      map((x: Context) => x.role === 'admin'),
      distinctUntilChanged(),
    );
  }

  login$({username, password}: { username: string; password: string; }): Observable<{ token: string; }> {
    return this.http.post<{ token: string; }>(createApiPath(SessionsPath), {
      username,
      password: sha256(password)
    }).pipe(
      tap((res) => {
        const metadata: any = jwtDecode(res.token);
        const user: Context = {token: res.token, username, userId: +metadata.sub, role: metadata.role};
        this._userContext$.next(user);
      }),
      tap(() => this.router.navigateByUrl('/')),
    );
  }

  register$({username, password}: { username: string; password: string; }): Observable<any> {
    return this.http.post(createApiPath(UsersPath), {
      username,
      password: sha256(password)
    });
  }

  logout() {
    this._userContext$.next(null);
    this.router.navigateByUrl('/login');
  }

}
