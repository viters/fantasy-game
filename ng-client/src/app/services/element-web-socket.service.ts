import { Injectable } from '@angular/core';
import { Observable, Observer, Subject } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ElementWebSocketService {
  private socket: Subject<MessageEvent>;

  constructor(private authService: AuthService) {
  }

  connect(): Subject<MessageEvent> {
    if (!this.socket) {
      this.socket = this.create(environment.wsUrl);
    }

    return this.socket;
  }

  private create(url): Subject<MessageEvent> {
    let ws = new WebSocket(url);

    ws.onopen = () => {
      this.authService.userContext$.pipe(
        map(u => u.token),
      ).subscribe(token => {
        ws.send(token);
      });
    };

    let observable = Observable.create(
      (obs: Observer<MessageEvent>) => {
        ws.onmessage = obs.next.bind(obs);
        ws.onerror = obs.error.bind(obs);
        ws.onclose = obs.complete.bind(obs);
        return ws.close.bind(ws);
      },
    );

    let observer = {
      next: () => null,
    };

    return Subject.create(observer, observable);
  }
}
