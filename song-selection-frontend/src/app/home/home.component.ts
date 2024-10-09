import { Component } from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {NgFor, NgIf} from "@angular/common";
import {SongService} from "../song.service";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, NgIf, NgFor],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  availableDates: string[] = [];
 constructor(private songService: SongService) {
  }

  ngOnInit(): void {
    this.songService.getAvailableDatesUseCase().subscribe(dates => {
      this.availableDates = dates;
    });
  }
}
