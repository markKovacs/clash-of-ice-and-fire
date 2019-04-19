import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import { WelcomeComponent } from './containers/welcome/welcome.component';

export const ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/welcome' },
  { path: 'welcome', component: WelcomeComponent},
  { path: 'map', loadChildren: '../map/map.module#BoardModule', canLoad: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(ROUTES)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule {}
