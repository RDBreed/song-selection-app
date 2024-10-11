import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = environment.authUrl;

  constructor(private http: HttpClient) {
  }

  login(credentials: { username: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/auth`, credentials, {
      withCredentials: true,
    });
  }

  checkTokenValidity(): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/protected`, {
      withCredentials: true,
    });
  }

  isAuthenticated(): Observable<any> {
    return this.checkTokenValidity();
  }

  logout() {
    return this.http.post(`${this.apiUrl}/auth/expired`, null,
      {
        withCredentials: true,
      });
  }
}
