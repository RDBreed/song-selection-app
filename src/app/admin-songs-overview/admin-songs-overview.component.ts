import { Component } from '@angular/core';
import { AdminSongService } from '../admin-song.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgFor, NgIf} from "@angular/common";
import {TokenInterceptorService} from "../token-interceptor.service";

@Component({
  selector: 'app-admin-songs-overview',
  templateUrl: './admin-songs-overview.component.html',
  styleUrl: './admin-songs-overview.component.scss',
  standalone: true,
  imports: [FormsModule, HttpClientModule, NgFor, NgIf],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}]
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
