import {ApplicationConfig} from '@angular/core';
import {provideRouter, Routes} from '@angular/router';
import {AdminAddDateComponent} from "./admin-add-date/admin-add-date.component";
import {SongSelectionComponent} from "./song-selection/song-selection.component";
import {HomeComponent} from "./home/home.component";
import {AdminSongsOverviewComponent} from "./admin-songs-overview/admin-songs-overview.component";
import { provideHttpClient, withInterceptors } from '@angular/common/http'
import {canActivate} from './auth.guard';
import {LoginComponent} from "./login/login.component";
import {tokenInterceptor} from "./token.interceptor";

const routes: Routes = [
  {path: 'admin/add-date', component: AdminAddDateComponent, canActivate: [canActivate]},
  {path: 'select-songs/:date', component: SongSelectionComponent},
  {path: '', component: HomeComponent},
  {path: 'admin/songs-overview', component: AdminSongsOverviewComponent, canActivate: [canActivate]},
  {path: 'login', component: LoginComponent},
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptors([tokenInterceptor]))
  ]
};
