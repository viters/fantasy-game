import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { createApiPath } from '../utils';
import { Category } from '../models/category';

const CategoriesPath = 'categories';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {
  }

  list$(): Observable<Category[]> {
    return this.http.get<Category[]>(createApiPath(CategoriesPath));
  }

  create$(category: Category): Observable<Category> {
    return this.http
      .post<Category>(createApiPath(CategoriesPath), category);
  }

  update$(category: Category): Observable<Category> {
    return this.http
      .put<Category>(createApiPath(CategoriesPath), category);
  }

  delete$(category: Category): Observable<Category> {
    return this.http
      .delete<Category>(createApiPath('category', category.id.toString()));
  }
}
