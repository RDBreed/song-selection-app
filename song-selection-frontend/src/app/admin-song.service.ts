import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AdminSongService {

private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }
    addDate(date: string): Observable<any>{
      return this.http.post(`${this.apiUrl}/admin/dates`, {date}, {
                                                                          withCredentials: true,
                                                                        })
    }

    getAdminSongOverview(date: string): Observable<any> {
      return this.http.get(`${this.apiUrl}/admin/songs-for-date?date=${date}`, {
                                                                                       withCredentials: true,
                                                                                     });
    }

    closeDate(date: string): Observable<any> {
        return this.http.post(`${this.apiUrl}/admin/dates/close`, { date }, {
                                                                                    withCredentials: true,
                                                                                  });
      }
}
