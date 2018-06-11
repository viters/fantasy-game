import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent {
  username$: Observable<string>;

  constructor(private authService: AuthService) {
    this.username$ = authService.userContext$.pipe(
      map(c => c.username),
    );
  }

}
