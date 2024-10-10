import {Component} from '@angular/core';
import {NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {AuthService} from "./auth.service";
import {NgFor, NgIf} from "@angular/common";
import {SongService} from "./song.service";
import {filter} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, NgIf, NgFor],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'song-selection-app';
  errorMessage = '';
  isAuthenticated = false;

  constructor(protected authService: AuthService, private router: Router, private songService: SongService) {
  }

  ngOnInit(): void {
    this.authService.isAuthenticated().subscribe({
      next: () => {
        this.isAuthenticated = true;
      },
      error: (err) => {
        this.isAuthenticated = false;
      }
    });

    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.authService.isAuthenticated().subscribe({
          next: () => {
            this.isAuthenticated = true;
          },
          error: (err) => {
            this.isAuthenticated = false;
          }
        });
      });
  }

  logout() {
    this.authService.logout().subscribe({
      next: (response: any) => {
        this.router.navigate(['/login']);
      },
      error: () => {
        this.errorMessage = 'Kan niet uitloggen...';
      }
    });
  }
}
