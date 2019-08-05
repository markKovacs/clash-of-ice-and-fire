import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppComponent } from './containers/app/app.component';
import { MaterialModule } from './material.module';
import { AuthModule } from '../auth/auth.module';
import { AppRoutingModule } from './app-routing.module';
import { StoreModule, MetaReducer } from '@ngrx/store';
import { UIService } from '../shared/services/ui.service';
import { HeaderComponent } from './components/header/header.component';
import { SidenavListComponent } from './components/sidenav-list/sidenav-list.component';
import { EffectsModule } from '@ngrx/effects';
import { reducers } from './store/app.reducer';
import { AuthService } from '../auth/auth.service';
import { WelcomeComponent } from './containers/welcome/welcome.component';
import { environment } from '../../environments/environment';

// not used in production
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { storeFreeze } from 'ngrx-store-freeze';


export const metaReducers: MetaReducer<any>[] = !environment.production
  ? [storeFreeze]
  : [];

@NgModule({
  declarations: [
    // CONTAINERS
    AppComponent,
    WelcomeComponent,
    // COMPONENTS
    HeaderComponent,
    SidenavListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    AuthModule,
    AppRoutingModule,
    StoreModule.forRoot(reducers, { metaReducers }),
    EffectsModule.forRoot([]),
    !environment.production ? StoreDevtoolsModule.instrument() : []
  ],
  providers: [
    UIService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
