import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CategoryService } from '../../services/category.service';
import { CategoryDictionaryService } from '../../services/category-dictionary.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  username$: Observable<string>;

  constructor(private authService: AuthService,
              private categoryService: CategoryService,
              private categoryDictionaryService: CategoryDictionaryService) {
  }

  ngOnInit() {
    this.username$ = this.authService.userContext$.pipe(
      map(c => c.username),
    );

    this.categoryDictionaryService.fetch();
  }
}
