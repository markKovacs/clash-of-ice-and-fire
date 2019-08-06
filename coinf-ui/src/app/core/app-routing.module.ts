import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import { WelcomeComponent } from './containers/welcome/welcome.component';
import { LobbyComponent } from './containers/lobby/lobby.component';
import { WelcomeGuard } from './store/guards/welcome.guard';
import { LobbyGuard } from './store/guards/lobby.guard';

export const ROUTES: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'welcome' },
  { path: 'welcome', component: WelcomeComponent, canActivate: [WelcomeGuard]},
  { path: 'lobby', component: LobbyComponent, canActivate: [LobbyGuard]},
  { path: 'map', loadChildren: '../board/board.module#BoardModule', canLoad: [AuthGuard] },
  { path: 'game', loadChildren: '../game/game.module#GameModule', canLoad: [AuthGuard] }

];

@NgModule({
  imports: [RouterModule.forRoot(ROUTES)],
  exports: [RouterModule],
  providers: [AuthGuard, WelcomeGuard, LobbyGuard]
})
export class AppRoutingModule {}
