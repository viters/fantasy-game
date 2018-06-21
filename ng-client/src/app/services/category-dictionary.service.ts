import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { createApiPath } from '../utils';
import { CategoryDictionary } from '../models/category-dictionary';
import { filter, tap } from 'rxjs/operators';

const CategoryDictionariesPath = 'category-dictionaries';

@Injectable({
  providedIn: 'root',
})
export class CategoryDictionaryService {
  private _dictionaries$ = new BehaviorSubject<CategoryDictionary[]>(null);

  constructor(private http: HttpClient) {
  }

  get dictionaries$(): Observable<CategoryDictionary[]> {
    return this._dictionaries$.pipe(filter(Boolean));
  }

  list$(): Observable<CategoryDictionary[]> {
    return this.http.get<CategoryDictionary[]>(createApiPath(CategoryDictionariesPath))
      .pipe(
        tap(x => this._dictionaries$.next(x)),
      );
  }

  create$(categoryDictionary: CategoryDictionary): Observable<CategoryDictionary> {
    return this.http
      .post<CategoryDictionary>(createApiPath(CategoryDictionariesPath), categoryDictionary);
  }

  update$(categoryDictionary: CategoryDictionary): Observable<CategoryDictionary> {
    return this.http
      .put<CategoryDictionary>(createApiPath(CategoryDictionariesPath), categoryDictionary);
  }

  delete$(categoryDictionary: CategoryDictionary): Observable<CategoryDictionary> {
    return this.http
      .delete<CategoryDictionary>(createApiPath(CategoryDictionariesPath, categoryDictionary.id.toString()));
  }

  fetch() {
    this.list$().subscribe(x => this._dictionaries$.next(x));
  }
}
