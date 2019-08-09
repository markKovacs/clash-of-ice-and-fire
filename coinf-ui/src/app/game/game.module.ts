import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameRoutingModule } from './game-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { GameComponent } from './containers/game/game.component';
import { NewGameComponent } from './components/new-game/new-game.component';
import { LobbyComponent } from './containers/lobby/lobby.component';

@NgModule({
  declarations: [
    // CONTAINERS
    LobbyComponent,
    GameComponent,
    // COMPONENTS
    NewGameComponent
  ],
  imports: [
    CommonModule,
    GameRoutingModule,
    FlexLayoutModule
  ]
})
export class GameModule {}
