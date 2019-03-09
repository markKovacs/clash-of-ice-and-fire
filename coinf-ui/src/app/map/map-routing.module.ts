import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WorldMapComponent } from './containers/world-map/world-map.component';

export const ROUTES: Routes = [
  { path: '', component: WorldMapComponent }
];

@NgModule({
  imports: [RouterModule.forChild(ROUTES)],
  exports: [RouterModule]
})
export class MapRoutingModule {}
