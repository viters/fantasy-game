import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { createApiPath } from '../utils';
import { Observable } from 'rxjs';
import { AppElement } from '../models/element';
import { map } from 'rxjs/operators';
import * as R from 'ramda';
import { Category } from '../models/category';

const RankingsPath = 'rankings';

@Injectable({
  providedIn: 'root',
})
export class RankingService {
  constructor(private http: HttpClient) {
  }

  getElementParam2RankingsByCategoryDictionary$(): Observable<{ [key: number]: AppElement[] }> {
    return this.http.get<AppElement[]>(createApiPath(RankingsPath, 'elements', 'param2'))
      .pipe(
        map(res => R.groupBy((i: Category) => i.categoryDictionaryId, res)),
      );
  }
}
