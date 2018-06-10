import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { distinctUntilChanged, map } from 'rxjs/operators';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
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

  login({username, password}: {username: string; password: string;}): Observable<any> {
    this._userContext$.next({credentials: {token: 'xD'}, username});
    this.router.navigate(['']);
    return null;
    // return this.http.post();
  }

  logout() {
    this._userContext$.next(null);
    this.router.navigate(['login']);
  }

}
