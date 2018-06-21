import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { createApiPath } from '../utils';
import { Category } from '../models/category';
import { map } from 'rxjs/operators';
import * as R from 'ramda';

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

  listByCategoryDictionary$(): Observable<{ [key: number]: Category[] }> {
    return this.list$().pipe(
      map(res => R.groupBy((i: Category) => i.categoryDictionaryId, res)),
    );
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
      .delete<Category>(createApiPath(CategoriesPath, category.id.toString()));
  }
}
