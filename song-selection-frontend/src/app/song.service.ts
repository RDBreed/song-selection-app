import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';
import { HttpClient } from '@angular/common/http';
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

  getAvailableDatesUseCase(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/dates`);
  }


  getSongsForDate(date: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/songs-for-date/${date}`);
  }

  searchSongs(term: string): Observable<any[]> {
      return this.http.get<any[]>(`${this.apiUrl}/songs/search?q=${term}`);
    }

  submitSongs(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/submit-songs`, data);
  }
}
