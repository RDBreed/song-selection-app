package eu.phaf.songselector.selection;

import eu.phaf.songselector.dates.GetAvailableDatesUseCase;

import java.time.LocalDate;

public class CreateSongSelectionUseCase {
  private final SongSelectionRepository songSelectionRepository;
  private final GetAvailableDatesUseCase getAvailableDatesUseCase;

  public CreateSongSelectionUseCase(SongSelectionRepository songSelectionRepository,
                                    GetAvailableDatesUseCase getAvailableDatesUseCase) {
    this.songSelectionRepository = songSelectionRepository;
    this.getAvailableDatesUseCase = getAvailableDatesUseCase;
  }

  public boolean create(SongSelection songSelection) {
    if (getAvailableDatesUseCase.getAllAvailableDates().contains(songSelection.date()) && !songSelection.songs().isEmpty()) {
      songSelectionRepository.create(songSelection);
      return true;
    }
    return false;
  }

  public SongSelection getSongSelectionForDate(LocalDate date) {
    return songSelectionRepository.getSongSelectionForDate(date);
  }
}
