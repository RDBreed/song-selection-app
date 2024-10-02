import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
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
  selectedSongs: string[] = ['', '', ''];  // Hier worden de gekozen nummers opgeslagen
  customSongs: string[] = ['', '', ''];    // Hier worden de eigen suggesties opgeslagen
  isCustomSong: boolean[] = [false, false, false]; // Of het een eigen suggestie is
  availableSongs: { title: string }[] = [];  // De lijst met beschikbare liederen
  motivation: string = '';

  constructor(private route: ActivatedRoute, private songService: SongService) {}

  ngOnInit(): void {
    // Haal de datum uit de URL
    this.route.paramMap.subscribe(params => {
      this.selectedDate = params.get('date')!;
    });

    // Haal de lijst van beschikbare liederen op
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

    this.songService.submitSongs(data).subscribe(response => {
      console.log('Liederen succesvol ingezonden', response);
    });
  }
}
