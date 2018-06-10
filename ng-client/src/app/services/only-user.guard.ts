import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { map, take } from 'rxjs/operators';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class OnlyUserGuard implements CanActivate {
  constructor(private router: Router,
              private authService: AuthService) {
  }

  canActivate() {
    return this.authService.isLogged$.pipe(
      take(1),
      map(isLogged => {
        if (isLogged) {
          return true;
        } else {
          this.router.navigate(['login']);
          return false;
        }
      }),
    );
  }
}
