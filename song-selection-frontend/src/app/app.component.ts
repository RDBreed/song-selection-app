import {Component} from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {HttpClientModule} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {NgFor, NgIf} from "@angular/common";
import {SongService} from "./song.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, HttpClientModule, NgIf, NgFor],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'song-selection-app';
  availableDates: string[] = [];

  constructor(protected authService: AuthService, private router: Router, private songService: SongService) {
  }

  ngOnInit(): void {
    // Haal de lijst van beschikbare datums op
    this.songService.getAvailableDates().subscribe(dates => {
      this.availableDates = dates;
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
