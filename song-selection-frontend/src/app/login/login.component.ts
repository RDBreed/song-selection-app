import {Component} from '@angular/core';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {FormsModule} from "@angular/forms";
import {NgFor, NgIf} from "@angular/common";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  login() {
    this.authService.login({username: this.username, password: this.password}).subscribe({
      next: (response: any) => {
        this.router.navigate(['/admin/songs-overview']);
      },
      error: () => {
        this.errorMessage = 'Ongeldige gebruikersnaam of wachtwoord';
      }
    });
  }
}
