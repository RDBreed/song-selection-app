package eu.phaf.songselector.dates;

import java.time.LocalDate;

public class CreateDateUseCase {
  private final DateRepository dateRepository;

  public CreateDateUseCase(DateRepository dateRepository) {
    this.dateRepository = dateRepository;
  }

  public boolean createDate(LocalDate date) {
    dateRepository.createDate(date);
    return true;
  }
}
