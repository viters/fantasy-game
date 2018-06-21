import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { createApiPath } from '../utils';
import { CategoryDictionary } from '../models/category-dictionary';
import { filter } from 'rxjs/operators';

const CategoryDictionariesPath = 'category-dictionaries';

@Injectable({
  providedIn: 'root'
})
export class CategoryDictionaryService {
  private _dictionaries$ = new BehaviorSubject<CategoryDictionary[]>(null);

  constructor(private http: HttpClient) {
  }

  get dictionaries$(): Observable<CategoryDictionary[]> {
    return this._dictionaries$.pipe(filter(Boolean));
  }

  fetch() {
    this.http.get<CategoryDictionary[]>(createApiPath(CategoryDictionariesPath))
      .subscribe(x => this._dictionaries$.next(x));
  }
}
