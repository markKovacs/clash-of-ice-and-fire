import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import { WelcomeComponent } from './containers/welcome/welcome.component';
import { WelcomeGuard } from './guards/welcome.guard';

export const ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'welcome' },
  { path: 'welcome', component: WelcomeComponent, canActivate: [WelcomeGuard]},
  { path: 'game', loadChildren: '../game/game.module#GameModule', canLoad: [AuthGuard] },
  { path: 'map', loadChildren: '../board/board.module#BoardModule', canLoad: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(ROUTES)],
  exports: [RouterModule],
  providers: [AuthGuard, WelcomeGuard]
})
export class AppRoutingModule {}
