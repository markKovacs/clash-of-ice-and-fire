import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MapComponent } from './containers/map/map.component';

export const ROUTES: Routes = [
  { path: '', component: MapComponent }
];

@NgModule({
  imports: [RouterModule.forChild(ROUTES)],
  exports: [RouterModule]
})
export class MapRoutingModule {}
