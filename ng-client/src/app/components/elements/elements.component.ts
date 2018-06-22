import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material';
import { ElementFormComponent } from './elements-form/element-form.component';
import { ElementService } from '../../services/element.service';
import { CategoryDictionary } from '../../models/category-dictionary';
import { AppElement } from '../../models/element';
import { CategoryDictionaryService } from '../../services/category-dictionary.service';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import * as R from 'ramda';

@Component({
  selector: 'app-elements',
  templateUrl: './elements.component.html',
  styleUrls: ['./elements.component.scss'],
})
export class ElementsComponent implements OnInit {
  elements: { [key: number]: AppElement[] };
  displayedColumns = ['index', 'category', 'param1', 'param2', 'param3', 'param4', 'actions'];
  categoryDictionaries$: Observable<CategoryDictionary[]>;
  selectedDictionary: CategoryDictionary;
  categories: Category[];

  constructor(private elementService: ElementService,
              private categoryService: CategoryService,
              private dialog: MatDialog,
              private categoryDictionaryService: CategoryDictionaryService) {
  }

  ngOnInit() {
    this.categoryDictionaries$ = this.categoryDictionaryService.dictionaries$;

    this.categoryDictionaries$.subscribe(x => this.selectedDictionary = x[0]);

    this.elementService.listByCategoryDictionary$().subscribe(r => this.elements = r);

    this.categoryService.list$().subscribe(x => this.categories = x);
  }

  getCategoryById(id) {
    return this.categories.find(c => c.id === id);
  }

  create() {
    let dialogRef = this.dialog.open(ElementFormComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((item: AppElement) => {
      if (item) {
        this.elements[item.categoryDictionaryId] = R.concat(this.elements[item.categoryDictionaryId] || [], [item]);
      }
    });
  }

  edit(element: AppElement) {
    let dialogRef = this.dialog.open(ElementFormComponent, {
      width: '400px',
      data: {element},
    });

    dialogRef.afterClosed().subscribe((item: AppElement) => {
      if (item) {
        const index = this.elements[item.categoryDictionaryId].findIndex(x => x.id === item.id);
        this.elements[item.categoryDictionaryId] = R.update(index, item, this.elements[item.categoryDictionaryId]);
      }
    });
  }

  delete(element: AppElement) {
    if (confirm('Are you sure you want to remove element no. ' + element.id)) {
      this.elementService.delete$(element).subscribe(() => {
        const index = this.elements[element.categoryDictionaryId].findIndex(x => x.id === element.id);
        this.elements[element.categoryDictionaryId] = R.remove(index, 1, this.elements[element.categoryDictionaryId]);
      });
    }
  }
}
