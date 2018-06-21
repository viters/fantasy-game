import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { map, take } from 'rxjs/operators';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class OnlyAdminGuard implements CanActivate {
  constructor(private router: Router,
              private authService: AuthService) {
  }

  canActivate() {
    return this.authService.isAdmin$.pipe(
      take(1),
      map(isAdmin => {
        if (isAdmin) {
          return true;
        } else {
          this.router.navigateByUrl('/');
          return false;
        }
      }),
    );
  }
}
