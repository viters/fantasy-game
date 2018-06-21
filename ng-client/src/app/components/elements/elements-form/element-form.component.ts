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
  updateId: number;
  categories: { [key: number]: Category };

  get isUpdate() {
    return this.updateId !== undefined;
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

    if (this.data && this.data.element) {
      this.elementForm = this.formBuilder.group({
        categoryDictionaryId: [this.data.element.categoryDictionaryId, Validators.required],
        categoryId: [this.data.element.categoryId, Validators.required],
        param1: [this.data.element.param1, Validators.required],
        param2: [this.data.element.param2, Validators.required],
        param3: [this.data.element.param3, Validators.required],
        param4: [this.data.element.param4, Validators.required],
      });

      this.updateId = this.data.element.id;
    } else {
      this.elementForm = this.formBuilder.group({
        categoryDictionaryId: [undefined, Validators.required],
        categoryId: [undefined, Validators.required],
        param1: [undefined, Validators.required],
        param2: [undefined, Validators.required],
        param3: [undefined, Validators.required],
        param4: [undefined, Validators.required],
      });
    }

    this.categoryService.listByCategoryDictionary$().subscribe(r => this.categories = r);
  }

  save() {
    if (this.elementForm.valid) {
      if (!this.isUpdate) {
        this.elementService.create$(this.elementForm.value)
          .subscribe((r) => this.dialogRef.close(r));
      } else {
        this.elementService.update$({id: this.updateId, ...this.elementForm.value})
          .subscribe((r) => this.dialogRef.close(r));
      }
    }
  }
}
