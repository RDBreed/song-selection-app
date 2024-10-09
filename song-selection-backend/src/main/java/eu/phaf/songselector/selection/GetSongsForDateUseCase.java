package eu.phaf.songselector.selection;

import java.time.LocalDate;

public class GetSongsForDateUseCase {
  private final SongSelectionRepository songSelectionRepository;

  public GetSongsForDateUseCase(SongSelectionRepository songSelectionRepository) {
    this.songSelectionRepository = songSelectionRepository;
  }

  public SongSelection getSongSelectionForDate(LocalDate date) {
    return songSelectionRepository.getSongSelectionForDate(date);
  }
}
