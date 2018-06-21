import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CategoryDictionaryService } from '../../../services/category-dictionary.service';

@Component({
  selector: 'app-metadata-form',
  templateUrl: './metadata-form.component.html',
  styleUrls: ['./metadata-form.component.scss'],
})
export class MetadataFormComponent implements OnInit {
  categoryDictionaryForm: FormGroup;

  get isUpdate() {
    return this.categoryDictionaryForm.get('id').value !== null;
  }

  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<MetadataFormComponent>,
              private categoryDictionaryService: CategoryDictionaryService,
              @Inject(MAT_DIALOG_DATA) private data: any) {
  }

  ngOnInit() {
    this.categoryDictionaryForm = this.formBuilder.group({
      id: [undefined],
      categoryName: [undefined, Validators.required],
      categoryParam1Name: [undefined, Validators.required],
      elementName: [undefined, Validators.required],
      elementParam1Name: [undefined, Validators.required],
      elementParam2Name: [undefined, Validators.required],
      elementParam3Name: [undefined, Validators.required],
      elementParam4Name: [undefined, Validators.required],
    });

    if (this.data && this.data.categoryDictionary) {
      this.categoryDictionaryForm.setValue(this.data.categoryDictionary);
    }
  }

  save() {
    if (this.categoryDictionaryForm.valid) {
      if (!this.isUpdate) {
        this.categoryDictionaryService.create$(this.categoryDictionaryForm.value)
          .subscribe((r) => this.dialogRef.close(r));
      } else {
        this.categoryDictionaryService.update$(this.categoryDictionaryForm.value)
          .subscribe((r) => this.dialogRef.close(r));
      }
    }
  }
}
