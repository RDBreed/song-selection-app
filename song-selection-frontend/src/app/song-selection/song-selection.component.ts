import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {SongService} from '../song.service';
import {NgFor, NgIf} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-song-selection',
  standalone: true,
  templateUrl: './song-selection.component.html',
  styleUrl: './song-selection.component.scss',
  imports: [FormsModule, NgFor, HttpClientModule, NgIf]
})
export class SongSelectionComponent implements OnInit {
  selectedDate: string = '';
  selectedSongs: string[] = ['', '', ''];
  customSongs: string[] = ['', '', ''];
  isCustomSong: boolean[] = [false, false, false];
  availableSongs: { title: string }[] = [];
  motivation: string = '';
  errorMessage: string = '';

  constructor(private route: ActivatedRoute, private songService: SongService, private router: Router) {}

  ngOnInit(): void {
    this.getDates()
    this.getAvailableSongs()
  }
  private getDates(){
   this.route.paramMap.subscribe(params => {
        this.selectedDate = params.get('date')!;
      });
  }

  private getAvailableSongs(){
   this.songService.getSongs().subscribe(songs => {
        this.availableSongs = songs;
      });
  }

  submitSongs() {
    const songsToSubmit = this.selectedSongs.map((song, index) => {
      return this.isCustomSong[index] ? this.customSongs[index] : song;
    });

    const data = {
      date: this.selectedDate,
      songs: songsToSubmit,
      motivation: this.motivation
    };

    this.songService.submitSongs(data).subscribe({
      next: (response: any) => {
        this.router.navigate(['/']);
    },
      error: () => {
            this.errorMessage = 'Versturen niet gelukt. Probeer opnieuw alstublieft.';
          }
    });
  }
}
