import { Component, OnInit } from '@angular/core';
import { RankingService } from '../../services/ranking.service';
import { AppElement } from '../../models/element';
import { CategoryDictionary } from '../../models/category-dictionary';
import { CategoryDictionaryService } from '../../services/category-dictionary.service';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-rankings',
  templateUrl: './rankings.component.html',
  styleUrls: ['./rankings.component.scss'],
})
export class RankingsComponent implements OnInit {
  param2Rankings: { [key: number]: AppElement[] };
  categoryDictionaries: CategoryDictionary[];
  displayedColumns = ['index', 'param2', 'author', 'category'];

  constructor(private rankingService: RankingService,
              private categoryDictionaryService: CategoryDictionaryService) {
  }

  ngOnInit() {
    this.rankingService.getElementParam2RankingsByCategoryDictionary$().subscribe(
      x => this.param2Rankings = x,
    );

    this.categoryDictionaryService.dictionaries$.pipe(
      take(1),
    ).subscribe(x => this.categoryDictionaries = x);
  }
}
