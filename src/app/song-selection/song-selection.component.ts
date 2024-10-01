import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';  // Import FormsModule

@Component({
  selector: 'app-song-selection',
  standalone: true,
  templateUrl: './song-selection.component.html',
  styleUrl: './song-selection.component.scss',
  imports: [FormsModule]
})
export class SongSelectionComponent implements OnInit {
  availableSongs: any[] = [];
    selectedSongs: any[] = ['', '', ''];  // Drie lege velden voor song keuzes
    customSongs: any[] = ['', '', ''];  // Optionele eigen liedjes
    selectedDate: string = '';
    motivation: string = '';

    constructor(private songService: SongService) {}

    ngOnInit(): void {
      this.songService.getAllSongs().subscribe(songs => {
        this.availableSongs = songs;
      });
    }

    submitSongs() {
      const songRequests = this.selectedSongs.map((song, index) => ({
        song: song || this.customSongs[index],  // Gebruik eigen song als geen nummer is gekozen
        motivation: this.motivation
      }));

      this.songService.addSongsForDate(this.selectedDate, songRequests).subscribe(response => {
        console.log('Songs submitted', response);
        this.selectedSongs = ['', '', ''];  // Reset na submit
        this.customSongs = ['', '', ''];
        this.motivation = '';
      });
    }
}
