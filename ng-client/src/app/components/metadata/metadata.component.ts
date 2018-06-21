import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { MetadataFormComponent } from './metadata-form/metadata-form.component';
import { CategoryDictionary } from '../../models/category-dictionary';
import { CategoryDictionaryService } from '../../services/category-dictionary.service';

@Component({
  selector: 'app-metadata',
  templateUrl: './metadata.component.html',
  styleUrls: ['./metadata.component.scss'],
})
export class MetadataComponent implements OnInit {
  categoryDictionaries: CategoryDictionary[];
  displayedColumns = ['index', 'categoryName', 'categoryParam1Name',
    'elementName', 'elementParam1Name', 'elementParam2Name',
    'elementParam3Name', 'elementParam4Name', 'actions'];

  constructor(private dialog: MatDialog,
              private categoryDictionaryService: CategoryDictionaryService) {
  }

  ngOnInit() {
    this.categoryDictionaryService.list$().subscribe(x => this.categoryDictionaries = x);
  }

  create() {
    let dialogRef = this.dialog.open(MetadataFormComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((item: CategoryDictionary) => {
      if (item) {
        this.categoryDictionaryService.list$().subscribe(x => this.categoryDictionaries = x);
      }
    });
  }

  edit(categoryDictionary: CategoryDictionary) {
    let dialogRef = this.dialog.open(MetadataFormComponent, {
      width: '400px',
      data: {categoryDictionary},
    });

    dialogRef.afterClosed().subscribe((item: CategoryDictionary) => {
      if (item) {
        this.categoryDictionaryService.list$().subscribe(x => this.categoryDictionaries = x);
      }
    });
  }

  delete(category: CategoryDictionary) {
    if (confirm('Are you sure you want to remove category type no. ' + category.id)) {
      this.categoryDictionaryService.delete$(category).subscribe(() => {
        this.categoryDictionaryService.list$().subscribe(x => this.categoryDictionaries = x);
      });
    }
  }
}
