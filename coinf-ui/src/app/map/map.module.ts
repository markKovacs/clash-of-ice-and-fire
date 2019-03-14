import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorldMapComponent } from './containers/world-map/world-map.component';
import { MapRoutingModule } from './map-routing.module';
import { MapComponent } from './components/map/map.component';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [
    WorldMapComponent,
    MapComponent
  ],
  imports: [
    CommonModule,
    MapRoutingModule,
    FlexLayoutModule
  ]
})
export class MapModule {}
