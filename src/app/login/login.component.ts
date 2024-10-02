import {Component} from '@angular/core';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NgFor, NgIf} from "@angular/common";
import {TokenInterceptorService} from "../token-interceptor.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule, NgFor, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  providers: [{provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}]
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
        localStorage.setItem('token', response.token);  // Token opslaan in localStorage
        this.router.navigate(['/']);  // Navigeer naar de homepagina na succesvol inloggen
      },
      error: () => {
        this.errorMessage = 'Ongeldige gebruikersnaam of wachtwoord';
      }
    });
  }
}
