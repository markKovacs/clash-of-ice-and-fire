import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorldMapComponent } from './containers/world-map/world-map.component';
import { BoardRoutingModule } from './board-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BoardComponent } from './components/board/board.component';
import { HexagonComponent } from './components/hexagon/hexagon.component';

@NgModule({
  declarations: [
    WorldMapComponent,
    BoardComponent,
    HexagonComponent
  ],
  imports: [
    CommonModule,
    BoardRoutingModule,
    FlexLayoutModule
  ]
})
export class BoardModule {}
