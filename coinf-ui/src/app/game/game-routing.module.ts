import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './containers/game/game.component';
import { LobbyComponent } from './containers/lobby/lobby.component';

export const ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'lobby' },
  { path: 'lobby', component: LobbyComponent, canActivate: [] }, // TODO: LobbyGuard implement
  { path: ':id', component: GameComponent, canActivate: [] } // TODO: GameGuard or multiple ones to implement
];

@NgModule({
  imports: [RouterModule.forChild(ROUTES)],
  exports: [RouterModule]
})
export class GameRoutingModule {}
