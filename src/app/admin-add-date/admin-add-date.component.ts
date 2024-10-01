import { Component } from '@angular/core';
import { AdminSongService } from '../admin-song.service';
import { FormsModule } from '@angular/forms';  // Import FormsModule

@Component({
  selector: 'app-admin-add-date',
  standalone: true,
  templateUrl: './admin-add-date.component.html',
  styleUrl: './admin-add-date.component.scss',
  imports: [FormsModule]
})
export class AdminAddDateComponent {
  selectedDate: string = '';
constructor(private songService: AdminSongService) {}

  addDate() {
    if (this.selectedDate) {
      this.songService.addDate(this.selectedDate).subscribe(response => {
        console.log('Date added successfully', response);
        this.selectedDate = '';  // Reset
      });
    }
  }
}
