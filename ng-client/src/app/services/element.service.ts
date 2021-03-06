import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { createApiPath } from '../utils';
import { Observable } from 'rxjs';
import { AppElement } from '../models/element';
import { map } from 'rxjs/operators';
import * as R from 'ramda';

const ElementsPath = 'elements';

@Injectable({
  providedIn: 'root',
})
export class ElementService {
  constructor(private http: HttpClient) {
  }

  list$(): Observable<AppElement[]> {
    return this.http.get<AppElement[]>(createApiPath(ElementsPath));
  }

  listByCategoryDictionary$(): Observable<{ [key: number]: AppElement[] }> {
    return this.list$().pipe(
      map(res => R.groupBy((i: AppElement) => i.categoryDictionaryId, res)),
    );
  }

  create$(element: AppElement): Observable<AppElement> {
    return this.http
      .post<AppElement>(createApiPath(ElementsPath), element);
  }

  update$(element: AppElement): Observable<AppElement> {
    return this.http
      .put<AppElement>(createApiPath(ElementsPath), element);
  }

  delete$(element: AppElement): Observable<AppElement> {
    return this.http
      .delete<AppElement>(createApiPath(ElementsPath, element.id.toString()));
  }
}
