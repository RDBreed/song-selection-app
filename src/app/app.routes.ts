import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminAddDateComponent } from './admin-add-date/admin-add-date.component';
import { SongSelectionComponent } from './song-selection/song-selection.component';
import { AdminSongsOverviewComponent } from './admin-songs-overview/admin-songs-overview.component';

const routes: Routes = [
  { path: 'admin/add-date', component: AdminAddDateComponent },
  { path: 'select-songs', component: SongSelectionComponent },
  { path: 'admin/songs-overview', component: AdminSongsOverviewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
