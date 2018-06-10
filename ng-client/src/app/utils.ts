import { environment } from '../environments/environment';

export function createApiPath(...part: string[]) {
  return [environment.apiUrl, ...part].join('/');
}
