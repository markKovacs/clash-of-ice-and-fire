import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorldMapComponent } from './containers/world-map/world-map.component';
import { MapRoutingModule } from './map-routing.module';
import { MapComponent } from './components/map/map.component';

@NgModule({
  declarations: [
    WorldMapComponent,
    MapComponent
  ],
  imports: [
    CommonModule,
    MapRoutingModule
  ]
})
export class MapModule {}
