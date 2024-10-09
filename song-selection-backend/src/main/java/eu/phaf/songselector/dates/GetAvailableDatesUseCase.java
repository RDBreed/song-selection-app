package eu.phaf.songselector.dates;

import java.time.LocalDate;
import java.util.List;

public class GetAvailableDatesUseCase {
  private final DateRepository dateRepository;

  public GetAvailableDatesUseCase(DateRepository dateRepository) {
    this.dateRepository = dateRepository;
  }

  public List<LocalDate> getAllAvailableDates() {
    return dateRepository.getAvailableDates();
  }
}
