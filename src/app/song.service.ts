import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SongService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getSongs(): Observable<any> {
    return this.http.get(`${this.apiUrl}/songs`)
  }

  addSongsForDate(date: string, songRequests: any[]): Observable<any> {
    return this.http.post(`${this.apiUrl}/songs-for-date`, {date, songRequests});
  }


  getSongsForDate(date: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/songs-for-date/${date}`);
  }
}
