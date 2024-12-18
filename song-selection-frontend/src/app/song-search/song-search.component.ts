import {Component, EventEmitter, Input, Output} from '@angular/core';
import {SongService} from '../song.service';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {Subject} from 'rxjs';
import {NgFor, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-song-search',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './song-search.component.html',
  styleUrl: './song-search.component.scss'
})
export class SongSearchComponent {
  @Output() songSelected = new EventEmitter<string>();
  @Input() selectedSong: string = '';
  private searchTerm$ = new Subject<string>();
  results: any[] = [];

  constructor(private songService: SongService) {
  }

  ngOnInit() {
    this.searchTerm$
      .pipe(
        debounceTime(300),
        distinctUntilChanged()
      )
      .subscribe((term: string) => {
        this.songService.searchSongs(term).subscribe(songs => {
          this.results = songs;
        });
      });
  }

  onSearch(event: Event): void {
    const input = event.target as HTMLInputElement;
    const term = input.value;

    if (term.length > 2) {
      this.searchTerm$.next(term);
    } else {
      this.results = [];
    }
  }

  selectSong(songTitle: string) {
    this.songSelected.emit(songTitle);
    this.results = [];
    this.selectedSong = ''
  }
}
