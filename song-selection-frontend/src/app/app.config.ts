import {ApplicationConfig} from '@angular/core';
import {provideRouter, Routes} from '@angular/router';
import {AdminAddDateComponent} from "./admin-add-date/admin-add-date.component";
import {SongSelectionComponent} from "./song-selection/song-selection.component";
import {AdminSongsOverviewComponent} from "./admin-songs-overview/admin-songs-overview.component";
import {provideHttpClient} from '@angular/common/http'
import {canActivate} from './auth.guard';
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: 'admin/add-date', component: AdminAddDateComponent, canActivate: [canActivate]},
  {path: 'select-songs/:date', component: SongSelectionComponent},
  {path: 'admin/songs-overview', component: AdminSongsOverviewComponent, canActivate: [canActivate]},
  {path: 'login', component: LoginComponent},
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient()
  ]
};
