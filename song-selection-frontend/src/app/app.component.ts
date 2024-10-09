import {Component} from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {NgFor, NgIf} from "@angular/common";
import {SongService} from "./song.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, NgIf, NgFor],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'song-selection-app';

  constructor(protected authService: AuthService, private router: Router, private songService: SongService) {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
