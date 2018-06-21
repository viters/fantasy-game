import { Component, OnInit } from '@angular/core';
import { CategoryDictionaryService } from './services/category-dictionary.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(private categoryDictionaryService: CategoryDictionaryService) {
  }

  ngOnInit() {
    this.categoryDictionaryService.fetch();
  }
}
