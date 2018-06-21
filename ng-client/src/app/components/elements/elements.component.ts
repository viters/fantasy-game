import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material';
import { ElementFormComponent } from './elements-form/element-form.component';
import { map } from 'rxjs/operators';
import * as R from 'ramda';
import { ElementService } from '../../services/element.service';
import { CategoryDictionary } from '../../models/category-dictionary';
import { AppElement } from '../../models/element';
import { CategoryDictionaryService } from '../../services/category-dictionary.service';

@Component({
  selector: 'app-elements',
  templateUrl: './elements.component.html',
  styleUrls: ['./elements.component.scss'],
})
export class ElementsComponent implements OnInit {
  elements: AppElement[];
  displayedColumns = ['index', 'param1', 'param2', 'param3', 'param4', 'actions'];
  categoryDictionaries$: Observable<CategoryDictionary[]>;
  selectedDictionary: CategoryDictionary;

  constructor(private elementService: ElementService,
              private dialog: MatDialog,
              private categoryDictionaryService: CategoryDictionaryService) {
  }

  ngOnInit() {
    this.categoryDictionaries$ = this.categoryDictionaryService.dictionaries$;

    this.categoryDictionaries$.subscribe(x => this.selectedDictionary = x[0]);

    this.elementService.list$().pipe(
      map(res => R.groupBy((i: AppElement) => i.categoryDictionaryId, res)),
    ).subscribe(r => this.elements = r);
  }

  create() {
    let dialogRef = this.dialog.open(ElementFormComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(console.log);
  }

  edit(element: AppElement) {
    let dialogRef = this.dialog.open(ElementFormComponent, {
      width: '400px',
      data: {element, selectedDictionary: this.selectedDictionary},
    });

    dialogRef.afterClosed().subscribe(console.log);
  }

  delete(element: AppElement) {
    if (confirm('Are you sure you want to remove element no. ' + element.id)) {
      this.elementService.delete$(element).subscribe(console.log);
    }
  }
}
