import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CategoryDictionaryService } from '../../../services/category-dictionary.service';
import { Observable } from 'rxjs';
import { CategoryDictionary } from '../../../models/category-dictionary';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.scss'],
})
export class CategoryFormComponent implements OnInit {
  categoryForm: FormGroup;
  categoryDictionaries$: Observable<CategoryDictionary[]>;

  get isUpdate() {
    return this.categoryForm.get('id').value !== null;
  }

  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<CategoryFormComponent>,
              private categoryService: CategoryService,
              private categoryDictionaryService: CategoryDictionaryService,
              @Inject(MAT_DIALOG_DATA) private data: any) {
  }

  ngOnInit() {
    this.categoryDictionaries$ = this.categoryDictionaryService.dictionaries$;

    this.categoryForm = this.formBuilder.group({
      id: [null],
      authorId: [null],
      categoryDictionaryId: [null, Validators.required],
      param1: [null, Validators.required],
    });

    if (this.data && this.data.category) {
      this.categoryForm.setValue(this.data.category);
    }
  }

  save() {
    if (this.categoryForm.valid) {
      if (!this.isUpdate) {
        this.categoryService.create$(this.categoryForm.value)
          .subscribe((r) => this.dialogRef.close(r));
      } else {
        this.categoryService.update$(this.categoryForm.value)
          .subscribe((r) => this.dialogRef.close(r));
      }
    }
  }
}
