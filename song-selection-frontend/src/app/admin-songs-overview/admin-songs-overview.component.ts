import { Component } from '@angular/core';
import { AdminSongService } from '../admin-song.service';
import { SongService } from '../song.service';
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgFor, NgIf} from "@angular/common";

@Component({
  selector: 'app-admin-songs-overview',
  templateUrl: './admin-songs-overview.component.html',
  styleUrl: './admin-songs-overview.component.scss',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
})
export class AdminSongsOverviewComponent {
  selectedDate: string = '';
  songOverview: any[] = [];
  availableDates: string[] = [];

  constructor(private adminSongService: AdminSongService, private songService: SongService) {
    this.loadAvailableDates()
  }

  private loadAvailableDates() {
    this.songService.getAvailableDatesUseCase().subscribe(dates => {
      this.availableDates = dates;
    });
  }

  getSongOverview() {
    if (this.selectedDate) {
      this.adminSongService.getAdminSongOverview(this.selectedDate).subscribe(overview => {
        this.songOverview = overview;
      });
    }
  }
}
