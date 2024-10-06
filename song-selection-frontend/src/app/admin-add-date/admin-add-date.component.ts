import {Component} from '@angular/core';
import {AdminSongService} from '../admin-song.service';
import {FormsModule} from '@angular/forms';
import {firstValueFrom} from 'rxjs';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-admin-add-date',
  standalone: true,
  templateUrl: './admin-add-date.component.html',
  styleUrl: './admin-add-date.component.scss',
  imports: [FormsModule, HttpClientModule],
})
export class AdminAddDateComponent {
  selectedDate: string = '';

  constructor(private songService: AdminSongService) {
  }

  async addDate() {
    if (this.selectedDate) {
      try {
        const response = await firstValueFrom(this.songService.addDate(this.selectedDate));
        console.log('Date added successfully', response);
        this.selectedDate = '';  // Reset
      } catch (error) {
        console.error('Error adding date', error);
      }
    }
  }
}
