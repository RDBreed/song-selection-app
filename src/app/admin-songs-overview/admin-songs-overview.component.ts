import { Component } from '@angular/core';
import { AdminSongService } from '../admin-song.service';

@Component({
  selector: 'app-admin-songs-overview',
  templateUrl: './admin-songs-overview.component.html',
})
export class AdminSongsOverviewComponent {
  selectedDate: string = '';
  songOverview: any[] = [];

  constructor(private songService: AdminSongService) {}

  getSongOverview() {
    if (this.selectedDate) {
      this.songService.getAdminSongOverview(this.selectedDate).subscribe(overview => {
        this.songOverview = overview;
      });
    }
  }
}
