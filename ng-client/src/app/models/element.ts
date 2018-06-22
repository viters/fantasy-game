import { User } from './user';
import { Category } from './category';

export interface AppElement {
  id: number;
  param1: string;
  param2: number;
  param3: number;
  param4: number;
  authorId: number;
  authorDTO?: User;
  categoryDTO?: Category;
  categoryId: number;
  categoryDictionaryId: number;
}
