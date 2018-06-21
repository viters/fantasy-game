import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material';
import { CategoryFormComponent } from './category-form/category-form.component';
import { CategoryDictionaryService } from '../../services/category-dictionary.service';
import { Category } from '../../models/category';
import { CategoryDictionary } from '../../models/category-dictionary';
import * as R from 'ramda';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
})
export class CategoriesComponent implements OnInit {
  categories: { [key: number]: Category[] };
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

    this.categoryService.listByCategoryDictionary$().subscribe(r => this.categories = r);
  }

  create() {
    let dialogRef = this.dialog.open(CategoryFormComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((item: Category) => {
      if (item) {
        this.categories[item.categoryDictionaryId] = R.concat(this.categories[item.categoryDictionaryId], [item]);
      }
    });
  }

  edit(category: Category) {
    let dialogRef = this.dialog.open(CategoryFormComponent, {
      width: '400px',
      data: {category},
    });

    dialogRef.afterClosed().subscribe((item: Category) => {
      if (item) {
        const index = this.categories[item.categoryDictionaryId].findIndex(x => x.id === item.id);
        this.categories[item.categoryDictionaryId] = R.update(index, item, this.categories[item.categoryDictionaryId]);
      }
    });
  }

  delete(category: Category) {
    if (confirm('Are you sure you want to remove category no. ' + category.id)) {
      this.categoryService.delete$(category).subscribe(() => {
        const index = this.categories[category.categoryDictionaryId].findIndex(x => x.id === category.id);
        this.categories[category.categoryDictionaryId] = R.remove(index, 1, this.categories[category.categoryDictionaryId]);
      });
    }
  }
}
