import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {SongService} from '../song.service';
import {NgFor} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-song-selection',
  standalone: true,
  templateUrl: './song-selection.component.html',
  styleUrl: './song-selection.component.scss',
  imports: [FormsModule, NgFor, HttpClientModule]
})
export class SongSelectionComponent implements OnInit {
  availableSongs: any[] = [];
  selectedSongs: any[] = ['', '', ''];
  customSongs: any[] = ['', '', ''];
  selectedDate: string = '';
  motivation: string = '';

  constructor(private songService: SongService) {
  }

  ngOnInit(): void {
    this.songService.getSongs().subscribe(songs => {
      this.availableSongs = songs;
    });
  }

  submitSongs() {
    const songRequests = this.selectedSongs.map((song, index) => ({
      song: song || this.customSongs[index],
      motivation: this.motivation
    }));

    this.songService.addSongsForDate(this.selectedDate, songRequests).subscribe(response => {
      console.log('Songs submitted', response);
      this.selectedSongs = ['', '', ''];
      this.customSongs = ['', '', ''];
      this.motivation = '';
    });
  }
}
