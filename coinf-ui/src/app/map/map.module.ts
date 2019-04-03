import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorldMapComponent } from './containers/world-map/world-map.component';
import { MapRoutingModule } from './map-routing.module';
import { MapComponent } from './components/map/map.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BoardComponent } from './components/board/board.component';
import { HexagonComponent } from './components/hexagon/hexagon.component';
import { CharacterComponent } from './components/character/character.component';

@NgModule({
  declarations: [
    WorldMapComponent,
    MapComponent,
    BoardComponent,
    HexagonComponent,
    CharacterComponent
  ],
  imports: [
    CommonModule,
    MapRoutingModule,
    FlexLayoutModule
  ]
})
export class MapModule {}
