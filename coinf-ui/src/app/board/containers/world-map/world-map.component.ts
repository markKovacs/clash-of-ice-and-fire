import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import * as fromRoot from '../../../core/store/app.reducer';
import { Store } from '@ngrx/store';
import { BOARD_DATA } from '../../service/board-mock-data';
import { Game } from 'src/app/shared/models/game.interface';

@Component({
  selector: 'app-world-map',
  templateUrl: './world-map.component.html',
  styleUrls: ['./world-map.component.css']
})
export class WorldMapComponent implements OnInit {

  boardData = BOARD_DATA;

  private game: Game;

  constructor(
    private authService: AuthService,
    private store: Store<fromRoot.State>
  ) {}

  ngOnInit(): void {
    // TODO: could get game here as well
  }

  getGame() {
    this.authService.getGame().subscribe(
        data => this.game = data,
        error => console.log(error)
      );
  }

  createGame() {
    this.authService.createGame().subscribe();
  }

}
