import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorldMapComponent } from './containers/world-map/world-map.component';
import { MapRoutingModule } from './map-routing.module';
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
    MapRoutingModule,
    FlexLayoutModule
  ]
})
export class MapModule {}
