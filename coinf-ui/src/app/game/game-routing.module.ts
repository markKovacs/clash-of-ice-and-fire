import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './containers/game/game.component';

export const ROUTES: Routes = [
  { path: '', component: GameComponent }
];

@NgModule({
  imports: [RouterModule.forChild(ROUTES)],
  exports: [RouterModule]
})
export class GameRoutingModule {}
