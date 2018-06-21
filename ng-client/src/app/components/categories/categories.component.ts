import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material';
import { CategoryFormComponent } from './category-form/category-form.component';
import { CategoryDictionaryService } from '../../services/category-dictionary.service';
import { map } from 'rxjs/operators';
import * as R from 'ramda';
import { Category } from '../../models/category';
import { CategoryDictionary } from '../../models/category-dictionary';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
})
export class CategoriesComponent implements OnInit {
  categories: Category[];
  displayedColumns = ['index', 'param1', 'actions'];
  categoryDictionaries$: Observable<CategoryDictionary[]>;
  selectedDictionary: CategoryDictionary;

  constructor(private categoryService: CategoryService,
              private dialog: MatDialog,
              private categoryDictionaryService: CategoryDictionaryService) {
  }

  ngOnInit() {
    this.categoryDictionaries$ = this.categoryDictionaryService.dictionaries$;

    this.categoryDictionaries$.subscribe(x => this.selectedDictionary = x[0]);

    this.categoryService.list$().pipe(
      map(res => R.groupBy((i: Category) => i.categoryDictionaryId, res)),
    ).subscribe(r => this.categories = r);
  }

  create() {
    let dialogRef = this.dialog.open(CategoryFormComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(console.log);
  }

  edit(category) {
    let dialogRef = this.dialog.open(CategoryFormComponent, {
      width: '400px',
      data: {category, selectedDictionary: this.selectedDictionary},
    });

    dialogRef.afterClosed().subscribe(console.log);
  }

  delete(category) {
    if (confirm('Are you sure you want to remove category no. ' + category.id)) {
      this.categoryService.delete$(category).subscribe(console.log);
    }
  }
}
