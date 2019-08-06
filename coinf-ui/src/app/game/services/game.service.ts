import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

import { User } from '../../shared/models/user.interface';

@Injectable()
export class GameService {

  constructor(private http: HttpClient) {}

  createNewGame(payload: User[]): Observable<boolean> {
    return this.http
      .post<boolean>(`/game/new`, payload)
      .pipe(catchError((error: any) => throwError(error)));
  }

}
