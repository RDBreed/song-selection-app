import { Component, OnInit } from '@angular/core';
import { SongService } from '../song.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {} from '@angular/common/http';
import { SongSearchComponent } from '../song-search/song-search.component'; // Import the new component

@Component({
  selector: 'app-song-selection',
  standalone: true,
  templateUrl: './song-selection.component.html',
  styleUrl: './song-selection.component.scss',
  imports: [FormsModule, NgFor, NgIf, SongSearchComponent]
})
export class SongSelectionComponent implements OnInit {
  selectedDate: string = '';
  selectedSongs: string[] = ['', '', ''];
  customSongs: string[] = ['', '', ''];
  isCustomSong: boolean[] = [false, false, false];
  motivation: string = '';
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private songService: SongService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.selectedDate = params.get('date')!;
    });
  }

  onSongSelected(song: string, index: number) {
    this.selectedSongs[index] = song; // Set the selected song for the correct index
    // Focus on the next input field if it exists
    const nextInputIndex = index;
    const nextInput = document.querySelectorAll('app-song-search input')[nextInputIndex];

    if (nextInput) {
      // Check if the next input field is a custom song or another search component
      (nextInput as HTMLElement).focus();
    }
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
