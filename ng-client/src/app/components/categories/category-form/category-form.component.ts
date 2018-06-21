import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CategoryDictionaryService } from '../../../services/category-dictionary.service';
import { Observable } from 'rxjs/index';
import { CategoryDictionary } from '../../../models/category-dictionary';
import { CategoryService } from '../../../services/category.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.scss'],
})
export class CategoryFormComponent implements OnInit {
  categoryForm: FormGroup;
  categoryDictionaries$: Observable<CategoryDictionary[]>;
  updateId: number;

  get isUpdate() {
    return this.updateId !== undefined;
  }

  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<CategoryFormComponent>,
              private categoryService: CategoryService,
              private categoryDictionaryService: CategoryDictionaryService,
              @Inject(MAT_DIALOG_DATA) private data: any) {
  }

  ngOnInit() {
    this.categoryDictionaries$ = this.categoryDictionaryService.dictionaries$;

    if (this.data && this.data.category) {
      this.categoryForm = this.formBuilder.group({
        categoryDictionaryId: [this.data.category.categoryDictionaryId, Validators.required],
        param1: [this.data.category.param1, Validators.required],
      });

      this.updateId = this.data.category.id;
    } else {
      this.categoryForm = this.formBuilder.group({
        categoryDictionaryId: [undefined, Validators.required],
        param1: ['', Validators.required],
      });
    }
  }

  save() {
    if (this.categoryForm.valid) {
      if (!this.isUpdate) {
        this.categoryService.create$(this.categoryForm.value)
          .subscribe((r) => this.dialogRef.close(r));
      } else {
        this.categoryService.update$({id: this.updateId, ...this.categoryForm.value})
          .subscribe((r) => this.dialogRef.close(r));
      }
    }
  }
}
