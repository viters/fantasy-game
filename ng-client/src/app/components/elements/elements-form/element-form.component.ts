import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CategoryDictionaryService } from '../../../services/category-dictionary.service';
import { Observable } from 'rxjs';
import { CategoryDictionary } from '../../../models/category-dictionary';
import { ElementService } from '../../../services/element.service';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-element-form',
  templateUrl: './element-form.component.html',
  styleUrls: ['./element-form.component.scss'],
})
export class ElementFormComponent implements OnInit {
  elementForm: FormGroup;
  categoryDictionaries$: Observable<CategoryDictionary[]>;
  categories: { [key: number]: Category[] };

  get isUpdate() {
    return this.elementForm.get('id').value !== null;
  }

  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<ElementFormComponent>,
              private elementService: ElementService,
              private categoryService: CategoryService,
              private categoryDictionaryService: CategoryDictionaryService,
              @Inject(MAT_DIALOG_DATA) private data: any) {
  }

  ngOnInit() {
    this.categoryDictionaries$ = this.categoryDictionaryService.dictionaries$;

    this.elementForm = this.formBuilder.group({
      id: [null],
      authorId: [null],
      categoryDictionaryId: [null, Validators.required],
      categoryId: [null, Validators.required],
      param1: [null, Validators.required],
      param2: [null, Validators.required],
      param3: [null, Validators.required],
      param4: [null, Validators.required],
    });

    if (this.data && this.data.element) {
      this.elementForm.setValue(this.data.element);
    }

    this.categoryService.listByCategoryDictionary$().subscribe(r => this.categories = r);
  }

  save() {
    if (this.elementForm.valid) {
      if (!this.isUpdate) {
        this.elementService.create$(this.elementForm.value)
          .subscribe((item) => this.dialogRef.close(item));
      } else {
        this.elementService.update$(this.elementForm.value)
          .subscribe((item) => this.dialogRef.close(item));
      }
    }
  }
}
