import { Pipe, PipeTransform } from '@angular/core';
import { CategoryDictionaryService } from '../services/category-dictionary.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Pipe({
  name: 'categoryMetadata',
})
export class CategoryMetadataPipe implements PipeTransform {
  constructor(private categoryDictionaryService: CategoryDictionaryService) {
  }

  transform(value: any, arg?: any): Observable<string> {
    return this.categoryDictionaryService.dictionaries$.pipe(
      map(x => x.find(d => d.id === +value)),
      map(x => x !== undefined && x !== null ? x[arg] : 'Unavailable')
    );
  }

}
